package com.phil.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class AuthFilter extends ZuulFilter {

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String reqUri = request.getRequestURI();
        if (!reqUri.startsWith("/auth"))//如果不是请求authserver
        {
            //从请求报文头中获取JWTToken
            String jwtToken = request.getHeader("JWTToken");
            if (jwtToken == null || jwtToken.length() <= 0) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpStatus.SC_BAD_REQUEST);
                ctx.setResponseBody("No JWTToken in http header");
                return null;
            }
            try {
                //尝试解密JWTToken
                Claims claims = Jwts.parser().setSigningKey("PHIL")// SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
                        .parseClaimsJws(jwtToken)// jwt是JWT字符串
                        .getBody();
                String userName = claims.get("userName", String.class);// 获取自定义字段key
                //把解析出来的用户名放到发给上游服务器的请求头中，这样上游服务器就不用自己解析了。而且上游服务器也不应该知道Zuul是用什么校验算法以及不用知道SecKey。
                //降低耦合性，具体的Rest服务不需要知道认证细节，Zuul修改认证机制以后也不用影响Rest服务
                ctx.addZuulRequestHeader("X-Req-UserName", userName);
            }
            // 如果密钥不正确或者数据被篡改，将会抛出SignatureException异常
            //如果JWT格式错误，会抛出MalformedJwtException异常
            // 如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常
            catch (SignatureException | MalformedJwtException ex) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpStatus.SC_BAD_REQUEST);
                ctx.setResponseBody("JWTToken validation error");
                return null;
            } catch (ExpiredJwtException e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                ctx.setResponseBody("JWTToken Expired");
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
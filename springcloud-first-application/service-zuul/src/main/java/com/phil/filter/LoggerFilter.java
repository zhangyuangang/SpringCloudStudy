package com.phil.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.phil.utils.HttpUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class LoggerFilter extends ZuulFilter {
  

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();//请求的类型，post get ..
        Map<String, String[]> params = request.getParameterMap();
        String paramsStr = params.toString();//请求的参数
        long statrtTime = (long) context.get("startTime");//请求的开始时间
        Throwable throwable = context.getThrowable();//请求的异常，如果有的话
        request.getRequestURI();//请求的uri
        HttpUtils.getIpAddress(request);//请求的iP地址
        context.getResponseStatusCode();//请求的状态
        long duration=(System.currentTimeMillis() - statrtTime);//请求耗时

        return null;
    }

}
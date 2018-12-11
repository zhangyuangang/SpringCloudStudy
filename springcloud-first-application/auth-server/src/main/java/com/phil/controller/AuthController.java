package com.phil.controller;

import com.phil.result.APIResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/")
public class AuthController {
    @RequestMapping("/requestToken")
    public APIResult requestToken(String userName, String password, HttpServletResponse response) throws Exception {
        if (userName.equals("zyg") && password.equals("123")) //这里假设只要用户名密码是admin、123就是验证成功，实际项目中肯定要查询数据库进行验证
        {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, 1);// 在当前基础上加一天
            String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "PHIL")// SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
                    .setExpiration(c.getTime())// expTime是过期时间
                    .claim("userName", userName).claim("userId", 5).compact();//模拟写死用户Id

            APIResult result = new APIResult(jwt, 200, "");
            return result;
        } else {
            APIResult result = new APIResult("", 400, "username or password error");
            return result;
        }
    }
}
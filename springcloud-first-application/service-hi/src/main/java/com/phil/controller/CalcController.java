package com.phil.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CalcController {

    @RequestMapping("/calc/add")
    public int add1(int i, int j, HttpServletRequest request) {
        String jWTToken = request.getHeader("JWTToken");
        System.out.println("jWTToken=" + jWTToken);

        //不用自己从jWTToken中读取，直接读取"X-Req-UserName"就行了
        //因为Zuul中完成了解析UserName并且放到报文头中了
        String userName = request.getHeader("X-Req-UserName");
        System.out.println("userName=" + userName);
        return i + j;
    }
}
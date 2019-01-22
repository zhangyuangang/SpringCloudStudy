package com.phil;

import org.junit.Ignore;
import org.junit.Test;

public class HelloAgentTest {
    @Ignore
    @Test
    public void helloTest(){
        System.out.println("hello word!");
        new UserServiceImpl().getUser();
    }
}

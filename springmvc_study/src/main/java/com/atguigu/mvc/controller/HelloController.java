package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HelloController {
//    @RequestMapping(value = "/" ,headers = {})
//    public String index(HttpServletRequest request){
//        return "index";
//    }
    @RequestMapping("/**/hello")
    public String hello(){
        return "redirect:/success";
    }
    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}

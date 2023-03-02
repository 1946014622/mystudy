package com.atguigu.nginx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@RequestMapping("nginx")
@Controller
public class NginxController {
    @GetMapping("search;")
    public void testNginx(){
        System.out.println("进入了8080端口的test方法");
    }
//    @GetMapping("img")
//    public String img() throws IOException {

//        URL url = new URL();
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setRequestMethod("GET");
//        urlConnection.setRequestProperty("message","api成功调用");
//        return " redirect:https://192.168.10.100/test/test.jpg";

//    }
}

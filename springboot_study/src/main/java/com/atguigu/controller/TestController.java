package com.atguigu.controller;

import com.atguigu.annotation.HelloAnnotation;
import com.atguigu.domin.entity.Car;
import com.atguigu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Api(tags = "测试文档")
@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${path.file}")
    private String filepath;
    @Value("${path.domain}")
    private String domain;
    @Autowired
    private UserService userService;
    @GetMapping("hello")
    @HelloAnnotation
    public void Hello(HttpServletResponse response){
        try {
            response.setCharacterEncoding("GBK");
            response.getWriter().print("你好");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @ApiOperation("文件上传")
    @PostMapping("fileUpLoad")
    public String fileUpLoad(@RequestPart("File") MultipartFile multipartFile) throws IOException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        File file = new File(filepath + format);
        if (!file.exists()) {
            file.mkdirs();
        }
        UUID uuid = UUID.randomUUID();

        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = uuid+ originalFilename;
        File f = new File(file, fileName);

        multipartFile.transferTo(f);
        return domain+format+"/"+fileName;

    }
//    @GetMapping("car")
//    public Car car(){
//        userService.test();
//        return car;
//    }
}

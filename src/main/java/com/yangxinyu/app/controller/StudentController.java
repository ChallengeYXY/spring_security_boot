package com.yangxinyu.app.controller;

import com.yangxinyu.app.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 星宇
 * @date : 2023/3/31 16:36
 * @description :
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @PostMapping("/sayHello")
    private Result<String> sayHello(){
        return Result.ok(200,"成功！","你好！");
    }
}

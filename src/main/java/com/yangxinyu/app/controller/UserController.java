package com.yangxinyu.app.controller;

import com.yangxinyu.app.entity.Result;
import com.yangxinyu.app.entity.Student;
import com.yangxinyu.app.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.controller
 * @Date : 2023/3/21 16:21
 * @Author : 星宇
 * @Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(Student student){
        loginService.login(student);
        return Result.ok(null);
    }
}

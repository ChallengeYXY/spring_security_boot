package com.yangxinyu.app.service;

import com.yangxinyu.app.entity.Student;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.service
 * @Date : 2023/3/21 16:45
 * @Author : 星宇
 * @Description :
 */
public interface LoginService {
    void login(Student student);
}

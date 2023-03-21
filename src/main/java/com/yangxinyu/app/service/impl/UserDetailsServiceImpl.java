package com.yangxinyu.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yangxinyu.app.entity.Student;
import com.yangxinyu.app.entity.User;
import com.yangxinyu.app.mapper.StudentMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.service.impl
 * @Date : 2023/3/21 13:46
 * @Author : 星宇
 * @Description :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库拿取用户
        Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
                .eq(Student::getUsername, username));
        //判断用户情况
        Objects.requireNonNull(student);

        //创建返回实体类
        return User.builder().student(student).build();
    }
}

package com.yangxinyu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangxinyu.app.auth.TokenUtils;
import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.auth.entity.CurrentUser;
import com.yangxinyu.app.converter.CurrentUserConverter;
import com.yangxinyu.app.entity.Student;
import com.yangxinyu.app.entity.Teacher;
import com.yangxinyu.app.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu
 * @Date : 2023/3/20 15:41
 * @Author : 星宇
 * @Description :
 */
@RunWith(SpringRunner.class)//以spring风格进行测试
@SpringBootTest//SpringBoot整合Junit4（org.junit.Test）
public class TestConfig {
    @Resource
    private JedisPool jedisPool;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private TokenProperties tokenProperties;

    /**
     * 测试Redis
     */
    @Test
    public void test01(){
        Jedis jedis = jedisPool.getResource();
        String pong = jedis.ping();
        System.out.println(pong);

    }

    /**
     * 测试mybatis
     */
    @Test
    public void test02(){
        //studentMapper.insert(new Student(null,"zhangsan","123456","张三",null));
        studentMapper.insert(new Student(null,"zhangsan",bCryptPasswordEncoder.encode("123456"),"张三",null));
    }

    /**
     * 测试jwt
     */
    @Test
    public void test03(){
        Student student = new Student(1,"zhangsan","123456","张三",null);
        CurrentUser currentUser = CurrentUserConverter.INSTANCE.studentToCurrentUser(student);
        String token = TokenUtils.createToken(tokenProperties, currentUser);
        System.out.println(token);
        CurrentUser currentUser1 = TokenUtils.fromToken(tokenProperties, token);
        System.out.println(currentUser1);

    }

    @Test
    public void test04() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(new Student(1,"zhangsan","123456","李四",new Teacher(1,"lisi","45678")));
        System.out.println(valueAsString);
        //{"id":1,"username":"zhangsan","password":"123456","teacher":{"id":1,"username":"lisi","password":"45678"}}
        Student student = objectMapper.readValue(valueAsString, Student.class);
        System.out.println(student);
        //Student(id=1, username=zhangsan, password=123456, teacher=Teacher(id=1, username=lisi, password=45678))
        System.out.println(student.getTeacher());
        //Teacher(id=1, username=lisi, password=45678)
    }

    @Test
    public void test05() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(new Student(1,"zhangsan","123456","李四",new Teacher(1,"lisi","45678")));
        System.out.println(valueAsString);

        String username = objectMapper.readTree(valueAsString).get("username").asText();
        System.out.println(username);
        String username1 = objectMapper.readTree(valueAsString).get("username").textValue();
        System.out.println(username1);
        String username2 = objectMapper.readTree(valueAsString).get("username").toString();
        System.out.println(username2);

        String teacher = objectMapper.readTree(valueAsString).get("teacher").asText();
        System.out.println(teacher);
        String teacher1 = objectMapper.readTree(valueAsString).get("teacher").textValue();
        System.out.println(teacher1);
        String teacher2 = objectMapper.readTree(valueAsString).get("teacher").toString();
        System.out.println(teacher2);

    }

    @Test
    public void test06() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(new Student(1,"zhangsan","123456","张三",new Teacher(1,"lisi","45678")));
        System.out.println(valueAsString);

        String username1= objectMapper.readTree(valueAsString).get("username").asText();
        System.out.println(username1);

        String username2 = objectMapper.readTree(valueAsString).findValue("username").asText();
        System.out.println(username2);

        String teacher = objectMapper.readTree(valueAsString).get("teacher").toString();
        System.out.println(teacher);

        String username3 = objectMapper.readTree(teacher).get("username").asText();
        System.out.println(username3);
    }
}

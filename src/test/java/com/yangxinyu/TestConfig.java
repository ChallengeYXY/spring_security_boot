package com.yangxinyu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangxinyu.app.entity.Student;
import com.yangxinyu.app.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void test01(){
        System.out.println(jedisPool.getClass());
        Jedis jedis = jedisPool.getResource();
        String pong = jedis.ping();
        System.out.println(pong);
    }

    @Test
    public void test02() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(new Student(1,"zhangsan","123456",new Teacher(1,"lisi","45678")));
        System.out.println(valueAsString);
        //{"id":1,"username":"zhangsan","password":"123456","teacher":{"id":1,"username":"lisi","password":"45678"}}
        Student student = objectMapper.readValue(valueAsString, Student.class);
        System.out.println(student);
        //Student(id=1, username=zhangsan, password=123456, teacher=Teacher(id=1, username=lisi, password=45678))
        System.out.println(student.getTeacher());
        //Teacher(id=1, username=lisi, password=45678)
    }

    @Test
    public void test03() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String valueAsString = objectMapper.writeValueAsString(new Student(1,"zhangsan","123456",new Teacher(1,"lisi","45678")));
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

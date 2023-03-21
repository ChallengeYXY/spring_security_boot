package com.yangxinyu.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.entity
 * @Date : 2023/3/20 16:28
 * @Author : 星宇
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_student")
public class Student {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    @TableField(exist = false)
    private Teacher teacher;
}

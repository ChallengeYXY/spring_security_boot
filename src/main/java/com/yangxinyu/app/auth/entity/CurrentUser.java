package com.yangxinyu.app.auth.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.auth.entity
 * @Date : 2023/3/21 10:04
 * @Author : 星宇
 * @Description :
 */
@Data
@Builder
public class CurrentUser {
    private Integer id;
}

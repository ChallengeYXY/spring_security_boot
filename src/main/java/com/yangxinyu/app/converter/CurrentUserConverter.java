package com.yangxinyu.app.converter;

import com.yangxinyu.app.auth.entity.CurrentUser;
import com.yangxinyu.app.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.converter
 * @Date : 2023/3/21 10:49
 * @Author : 星宇
 * @Description :
 */
@Mapper
public interface CurrentUserConverter {
    CurrentUserConverter INSTANCE = Mappers.getMapper(CurrentUserConverter.class);

    @Mappings({
            @Mapping(source = "id",target = "id")
    })
    CurrentUser studentToCurrentUser(Student student);
}

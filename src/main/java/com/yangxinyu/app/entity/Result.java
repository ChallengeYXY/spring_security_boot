package com.yangxinyu.app.entity;

import lombok.Data;

/**
 * @Date : 2023/3/21 16:27
 * @Author : 星宇
 * @Description :
 */

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <R> Result<R> ok(Integer code,String message,R data){
        return new Result<R>(code,message,data);
    }
    public static Result err(Integer code,String message){
        return new Result(code,message);
    }
}

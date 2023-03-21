package com.yangxinyu.app.entity;
/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.entity
 * @Date : 2023/3/21 16:27
 * @Author : 星宇
 * @Description :
 */

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

    public static <R> Result<R> ok(R data){
        return new Result<R>(200,"成功！",data);
    }
    public static Result err(){
        return new Result(500,"失败");
    }
}

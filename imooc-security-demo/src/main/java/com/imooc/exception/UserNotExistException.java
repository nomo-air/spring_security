package com.imooc.exception;

import lombok.Data;

@Data
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("自定义用户不存在异常");
        this.id = id;
    }

}

package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryCondition {

    @ApiModelProperty(value = "用户名")
    private String username;

    private int age;

    private int ageTo;

    private String xxx;
}

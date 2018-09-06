package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class User {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    @JsonView(UserSimpleView.class)
    private String id;

    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    @NotNull
    private String password;

    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去时间")
    private Date birthday;
}

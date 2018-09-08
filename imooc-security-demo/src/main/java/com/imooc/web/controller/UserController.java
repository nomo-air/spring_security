package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @ApiOperation(value = "用户查询")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition) {
        log.info("用户信息如下：{}", ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    // 支持正则
    @GetMapping(value = "/{id:\\d+}")
    @ApiOperation(value = "根据id获取用户详情")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id) {
        log.info("进入Controller，获取用户详情，用户不存在异常会被异常处理器处理，不会传到拦截器，拦截器不仅会拦截用户定义的Controller也会拦截" +
                "spring框架本身的Controller，拦截层级关系为：Controller -> Aspect -> ControllerAdvice -> Interceptor -> Filter");
        // throw new RuntimeException();
        throw new UserNotExistException(id);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.info("{}, {}, {}, {}", user.getId(), user.getUsername(), user.getPassword(), user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                // 可以获取错误详细信息
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + error.getDefaultMessage();
                log.info("{}", message);
            });
        }
        log.info("{}, {}, {}, {}", user.getId(), user.getUsername(), user.getPassword(), user.getBirthday());
        return user;
    }

    @GetMapping("/me2")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me1")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails authentication) {
        return authentication;
    }

}
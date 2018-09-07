package com.imooc.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
// 这里不用写注入
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    // 这里你可以用@Autowired注入

    @Override
    public void initialize(MyConstraint myConstraint) {
        log.info("{}", "自定义验证参数注解初始化");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 这里可以用注入的类来参与验证逻辑
        log.info("执行自定义校验参数逻辑：{}", value);
        return false;
    }
}

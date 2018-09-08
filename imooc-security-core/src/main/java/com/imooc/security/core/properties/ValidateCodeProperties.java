package com.imooc.security.core.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 图片验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}

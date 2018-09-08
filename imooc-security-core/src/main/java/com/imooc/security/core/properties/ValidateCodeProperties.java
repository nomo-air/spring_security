package com.imooc.security.core.properties;

import com.imooc.security.core.validate.code.ImageCodeProperties;
import lombok.Data;

@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
}

package com.example.zuul.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.zuul.commons
 * @description
 * @date 2020/4/4 14:42
 */
@Component
@ConfigurationProperties(prefix = "custom.url-config")
@Data
public class UrlConfig {

    /**
     * 免验证路径
     */
    private String[] annoUrls;

    /**
     * 可以通过的路径
     */
    private String[] annoIps;
}

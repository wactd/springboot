package com.dongly.test.controller;

import com.dongly.base.DataSourceModel;
import com.dongly.test.entity.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
public class HelloWordController {

    @Value("${user.username}")
    private String username;

    @Value("${user.age}")
    private Integer age;

    @Value("${user.description}")
    private String description;

    @Autowired
    private UserProperties properties;

    @Autowired
    private DataSourceModel sourceModel;

    @GetMapping(value = "/user/desc")
    public String desc() {
        return properties.getDescription();
    }

    @GetMapping(value = "/user")
    public String user() {
        return "姓名：" + username + " 年龄：" + age;
    }

}

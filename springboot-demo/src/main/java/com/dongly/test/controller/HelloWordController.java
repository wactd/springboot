package com.dongly.test.controller;

import com.dongly.base.DataSourceModel;
import com.dongly.test.entity.UserProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.thymeleaf.expression.Maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

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




    @GetMapping(value = "/async")
    public DeferredResult<Map<String, Object>> quotes() throws InterruptedException {
        DeferredResult<Map<String, Object>> deferredResult = new DeferredResult<>();
        Map<String, Object> result = new HashMap<>();
        result.put("abc", new Date());
        deferredResult.setResult(result);
        Thread.sleep(5000);
        result.put("", new Date());
        deferredResult.setResult(result);
        Thread.sleep(5000);
        return deferredResult;
    }

}

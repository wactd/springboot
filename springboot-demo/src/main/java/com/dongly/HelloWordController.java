package com.dongly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tiger on 17-5-21.
 */

@RestController
public class HelloWordController{

    @GetMapping(value = "/")
    public String hello() {
        return "Hello World(你好, 世界)!";
    }

}

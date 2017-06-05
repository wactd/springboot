package com.dongly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tiger on 17-6-5.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}

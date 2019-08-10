package com.benjamin.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:HelloController
 * Package:com.benjamin.springboot.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-8-10 下午3:55
 */

//@RestController = @Controller + @RequestBody
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "world!";
    }
}

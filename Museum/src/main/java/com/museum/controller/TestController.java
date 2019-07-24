package com.museum.controller;

import com.museum.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhao
 * @date 2019/7/24 13:50
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public Result test(){
        return new Result();
    }

}

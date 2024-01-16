package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DF00_HomeController {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/")
    public String home(){
//        logger.error("test");
//        logger.debug("한글");
//        logger.debug("1234");
        return "DF00_index";
    }
}

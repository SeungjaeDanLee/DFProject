package com.example.controller.DF00;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class DF00_ErrorController {

    @GetMapping("/400")
    public String error400(){
        return "/DF00_error/400error";
    }

    @GetMapping("/401")
    public String error401(){
        return "/DF00_error/401error";
    }

    @GetMapping("/402")
    public String error402(){
        return "/DF00_error/402error";
    }

    @GetMapping("/403")
    public String error403(){
        return "/DF00_error/403error";
    }

    @GetMapping("/404")
    public String error404(){
        return "/DF00_error/404error";
    }
}
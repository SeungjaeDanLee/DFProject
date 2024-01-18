package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DF00_AdminController {

    @GetMapping("/home")
    public String adminHomePage(){
        return "DF00_admin/DF0001_adminHome";
    }

    @GetMapping("/charts")
    public String adminChartsPage(){
        return "DF00_admin/DF0002_adminCharts";
    }

    @GetMapping("/tables")
    public String adminTablesPage(){
        return "DF00_admin/DF0003_adminTables";
    }
}

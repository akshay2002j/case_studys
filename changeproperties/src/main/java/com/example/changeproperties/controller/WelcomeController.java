package com.example.changeproperties.controller;

import com.example.changeproperties.config.DBConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class WelcomeController {


    private DBConfig DbConfig;

    public WelcomeController(DBConfig DbConfig) {
        this.DbConfig = DbConfig;
    }

    @GetMapping("welcome")
    public String welcome(){
        return "Welcome to " + DbConfig.getUsername();
    }


}

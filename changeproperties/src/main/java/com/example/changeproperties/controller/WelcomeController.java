package com.example.changeproperties.controller;

import com.example.changeproperties.config.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
@RefreshScope
public class WelcomeController {

    @Autowired
    private DBConfig DbConfig;

    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    public WelcomeController(DBConfig DbConfig) {
        this.DbConfig = DbConfig;
    }

    @Value("${app.message}")
    String name;

    @GetMapping("welcome")
    public String welcome(){
        log.info("In welcome Controller");
        log.error("Error in Healt");
        return  name;
    }


}

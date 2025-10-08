package com.example.changeproperties.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Component
@RefreshScope
@PropertySource("classpath:database.properties")
public class DBConfig {

    @Value("${data.db.baseurl}")
    private String dbUrl;
    @Value("${data.db.user}")
    private String username;
    @Value("${data.bd.password}")
    private String password;


    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.changeproperties.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data.db")
@RefreshScope
@PropertySource("classpath:database.properties")
public class DBConfig {

    @Value("${data.baseurl}")
    private String dbUrl;
    @Value("${data.db.user}")
    private String username;
    @Value("${data.bd.password}")
    private String password;


    public String getDbUrl() {
        return dbUrl;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

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

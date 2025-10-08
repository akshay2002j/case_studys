package com.example.changeproperties.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BeanOne {



    @PostConstruct
    public void init()
    {
        System.out.println("BeanOne init");
    }

    public  BeanOne()
    {
        System.out.println("BeanOne Const");
    }
}

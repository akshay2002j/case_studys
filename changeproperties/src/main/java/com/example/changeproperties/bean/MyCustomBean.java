package com.example.changeproperties.bean;



import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MyCustomBean {

   final private BeanOne beanOne;

    public MyCustomBean(BeanOne beanOne) {
        this.beanOne = beanOne;
        System.out.println("IN MyCustomeBean Constructor");
    }

    @PostConstruct
   public void init(){
       System.out.println("some initialization logic like connection opening");
   }
   @PreDestroy
   public void destroy(){
       System.out.println("destroy the resources used by application");
   }
}

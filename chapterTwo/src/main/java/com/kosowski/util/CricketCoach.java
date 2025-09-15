package com.kosowski.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    @PostConstruct
    public void init(){
        System.out.println("CricketCoach: inside init method. Do my bussiness stuff");
    }

    // If you use a bean with prototype scope, then Spring will NOT call the @PreDestroy method. This is by design.
    @PreDestroy
    public void destroy(){
        System.out.println("CricketCoach: inside destroy method. Clean up the resources");
    }

}

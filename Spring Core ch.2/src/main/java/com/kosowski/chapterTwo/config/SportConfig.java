package com.kosowski.chapterTwo.config;

import com.kosowski.chapterTwo.common.SwimCoach;
import com.kosowski.util.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("SomeBeanName")
    public Coach swimCoach() {
        return new SwimCoach();
    }

}

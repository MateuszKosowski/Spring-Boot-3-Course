package com.kosowski.chapterTwo.common;

import com.kosowski.util.Coach;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice batting practice";
    }
}

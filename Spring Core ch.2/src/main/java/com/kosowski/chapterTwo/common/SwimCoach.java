package com.kosowski.chapterTwo.common;

import com.kosowski.util.Coach;

public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("SwimCoach: inside default constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice swimming for 15 minutes";
    }


}

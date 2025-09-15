package com.kosowski.chapterTwo.rest;

import com.kosowski.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final Coach myCoach;
    private final Coach myAnotherCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("cricketCoach") Coach myAnotherCoach) {
        this.myCoach = myCoach;
        this.myAnotherCoach = myAnotherCoach;
    }

    @GetMapping("/coach")
    public String getCoach() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/checkCoachBean")
    public String check(){
        return "The same coach object? " + (myCoach == myAnotherCoach);
    }

}

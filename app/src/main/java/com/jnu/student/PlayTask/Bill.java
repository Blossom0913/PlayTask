package com.jnu.student.PlayTask;

import java.io.Serializable;
import java.util.Calendar;

public class Bill implements Serializable {

    private String name;

    private int score;

    private String time;

    public Bill(Task task){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        time = hour + " : " + minute + " : "+ second;

        name = task.getName();
        score = task.getScore();
    }

    public Bill(Reward reward){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        time = hour + " : " + minute + " : "+ second;
        name = reward.getName();
        score = reward.getScore();
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }
}

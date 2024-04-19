package com.jnu.student.PlayTask;

import java.io.Serializable;

public class Reward implements Serializable {
    private String name;

    private int score;

    private int FinishTimes = 0;

    public Reward(String name,int score){
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int finish(){
        this.FinishTimes++;
        return this.FinishTimes;
    }

    public int getFinishtime() {
        return FinishTimes;
    }
}

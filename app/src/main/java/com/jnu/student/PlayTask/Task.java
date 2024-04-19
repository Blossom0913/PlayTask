package com.jnu.student.PlayTask;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;

    private int score;

    private int TotalTimes;

    private int FinishTimes = 0;
    public Task(String name,int score,int TotalTimes){
        this.name = name;
        this.score = score;
        this.TotalTimes = TotalTimes;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getFinishtime() {
        return FinishTimes;
    }

    public int getTotalTimes() {
        return TotalTimes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalTimes(int times) {
        this.TotalTimes = times;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean finish(){
        this.FinishTimes++;
        if(this.FinishTimes == this.TotalTimes)
            return true;
        else
            return false;
    }
}

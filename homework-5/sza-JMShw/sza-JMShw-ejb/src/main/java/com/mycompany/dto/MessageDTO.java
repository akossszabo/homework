package com.mycompany.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable{
    private boolean done;
    private long time;
    private int number;
    private int jobTime;

    public int getJobTime() {
        return jobTime;
    }

    public void setJobTime(int jobTime) {
        this.jobTime = jobTime;
    }

    public MessageDTO() {
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
}

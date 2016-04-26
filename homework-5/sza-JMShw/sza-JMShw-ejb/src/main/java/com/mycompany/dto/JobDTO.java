package com.mycompany.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {
    private int number;
    private int estimatedTime;

    public JobDTO() {
    }

    public JobDTO(int estimatedTime, int number) {
        this.estimatedTime = estimatedTime;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "JobDTO{" + "estimatedTime=" + estimatedTime + '}';
    }
    
}

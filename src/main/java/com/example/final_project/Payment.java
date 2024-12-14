package com.example.final_project;

import java.io.Serializable;

public class Payment implements Serializable {
    private int user_id;
    private String user_name;
    private String userType;
    private Float amount;

    public Payment() {
    }

    public Payment(int user_id, String user_name, String userType, Float amount) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.userType = userType;
        this.amount = amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", userType='" + userType + '\'' +
                ", amount=" + amount +
                '}';
    }
}

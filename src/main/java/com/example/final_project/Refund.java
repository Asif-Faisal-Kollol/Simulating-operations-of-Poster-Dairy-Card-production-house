package com.example.final_project;

import java.io.Serializable;

public class Refund implements Serializable {
    private int Request_ID;
    private String product_name;
    private int amount;
    private String problem_details;

    public Refund() {
    }

    public Refund(int request_ID, String product_name, int amount, String problem_details) {
        Request_ID = request_ID;
        this.product_name = product_name;
        this.amount = amount;
        this.problem_details = problem_details;
    }

    public int getRequest_ID() {
        return Request_ID;
    }

    public void setRequest_ID(int request_ID) {
        Request_ID = request_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProblem_details() {
        return problem_details;
    }

    public void setProblem_details(String problem_details) {
        this.problem_details = problem_details;
    }


    @Override
    public String toString() {
        return "Refund{" +
                "Request_ID=" + Request_ID +
                ", product_name='" + product_name + '\'' +
                ", amount=" + amount +
                ", problem_details='" + problem_details + '\'' +
                '}';
    }
}

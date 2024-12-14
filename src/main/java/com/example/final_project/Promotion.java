package com.example.final_project;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Promotion implements Serializable {
    private String campaign_name;
    private ArrayList<String> product_list;
    private float discount_percentage;
    private LocalDate start_date;
    private LocalDate end_date;

    public Promotion() {
    }

    public Promotion(String campaign_name, ArrayList<String> product_list, float discount_percentage, LocalDate start_date, LocalDate end_date) {
        this.campaign_name = campaign_name;
        this.product_list = product_list;
        this.discount_percentage = discount_percentage;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public ArrayList<String> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(ArrayList<String> product_list) {
        this.product_list = product_list;
    }

    public float getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(float discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }


    @Override
    public String toString() {
        return "Promotion{" +
                "campaign_name='" + campaign_name + '\'' +
                ", product_list=" + product_list +
                ", discount_percentage=" + discount_percentage +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}

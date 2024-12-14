package com.example.final_project;

import java.io.Serializable;

public class Sales_Report implements Serializable {
    private int product_id;
    private String product_name;
    private int quantity_sold;
    private String category;
    private float revenue;

    public Sales_Report() {
    }

    public Sales_Report(int product_id, String product_name, int quantity_sold, String category, float revenue) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity_sold = quantity_sold;
        this.category = category;
        this.revenue = revenue;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Sales_Report{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", quantity_sold=" + quantity_sold +
                ", category='" + category + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}

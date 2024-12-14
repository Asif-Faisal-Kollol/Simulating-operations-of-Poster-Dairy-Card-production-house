package com.example.final_project;

import java.io.Serializable;

public class product implements Serializable {
    private int product_id;
    private String product_name;
    private int product_price;
    private String product_stock;

    public product() {
    }

    public product(int product_id, String product_name, int product_price, String product_stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_stock = product_stock;
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

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    @Override
    public String toString() {
        return "product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_stock='" + product_stock + '\'' +
                '}';
    }
}

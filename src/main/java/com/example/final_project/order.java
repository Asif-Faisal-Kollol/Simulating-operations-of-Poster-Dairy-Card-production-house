package com.example.final_project;

import java.io.Serializable;
import java.time.LocalDate;

public class order implements Serializable {
    private int order_id;
    private String customer_name;
    private LocalDate order_date;
    private String Address;
    private LocalDate delivery_date;
    private int ordered_amount;

    public order() {
    }

    public order(int order_id, String customer_name, LocalDate order_date, String address, LocalDate delivery_date, int ordered_amount) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.order_date = order_date;
        Address = address;
        this.delivery_date = delivery_date;
        this.ordered_amount = ordered_amount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }

    public int getOrdered_amount() {
        return ordered_amount;
    }

    public void setOrdered_amount(int ordered_amount) {
        this.ordered_amount = ordered_amount;
    }

    @Override
    public String toString() {
        return "order{" +
                "order_id=" + order_id +
                ", customer_name='" + customer_name + '\'' +
                ", order_date=" + order_date +
                ", Address='" + Address + '\'' +
                ", delivery_date=" + delivery_date +
                ", ordered_amount=" + ordered_amount +
                '}';
    }
}

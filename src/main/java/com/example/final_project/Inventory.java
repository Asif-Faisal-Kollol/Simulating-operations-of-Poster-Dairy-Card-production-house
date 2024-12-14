package com.example.final_project;

import java.io.Serializable;
import java.time.LocalDate;

public class Inventory implements Serializable {

    //private static final long serialVersionUID = 1L; // Ensure consistent deserialization

    private int product_id;
    private int order_id;
    private int available_amount;
    private int ordered_amount;
    private LocalDate order_date;
    private int deliveryAfterAmount;

    public Inventory() {
    }

    public Inventory(int product_id, int order_id, int available_amount, int ordered_amount, LocalDate order_date, int deliveryAfterAmount) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.available_amount = available_amount;
        this.ordered_amount = ordered_amount;
        this.order_date = order_date;
        this.deliveryAfterAmount = deliveryAfterAmount;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAvailable_amount() {
        return available_amount;
    }

    public void setAvailable_amount(int available_amount) {
        this.available_amount = available_amount;
    }

    public int getOrdered_amount() {
        return ordered_amount;
    }

    public void setOrdered_amount(int ordered_amount) {
        this.ordered_amount = ordered_amount;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public int getDeliveryAfterAmount() {
        return deliveryAfterAmount;
    }

    public void setDeliveryAfterAmount(int deliveryAfterAmount) {
        this.deliveryAfterAmount = deliveryAfterAmount;
    }

    // Calculate remaining amount
    public int calculateRemainingAmount() {
        return available_amount - ordered_amount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "product_id=" + product_id +
                ", order_id=" + order_id +
                ", available_amount=" + available_amount +
                ", ordered_amount=" + ordered_amount +
                ", order_date=" + order_date +
                ", deliveryAfterAmount=" + deliveryAfterAmount +
                '}';
    }
}
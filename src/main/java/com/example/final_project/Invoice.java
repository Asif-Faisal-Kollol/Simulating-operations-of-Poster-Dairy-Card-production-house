package com.example.final_project;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private int invoice_id;
    private String customer_name;
    private String order_description;
    private int amount;
    private LocalDate invoiceDate;

    public Invoice() {
    }

    public Invoice(int invoice_id, String customer_name, String order_description, int amount, LocalDate invoiceDate) {
        this.invoice_id = invoice_id;
        this.customer_name = customer_name;
        this.order_description = order_description;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = order_description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", customer_name='" + customer_name + '\'' +
                ", order_description='" + order_description + '\'' +
                ", amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                '}';
    }

}

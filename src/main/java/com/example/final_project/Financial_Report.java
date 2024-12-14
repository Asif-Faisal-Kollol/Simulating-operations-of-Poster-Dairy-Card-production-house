package com.example.final_project;

import java.io.Serializable;
import java.time.LocalDate;

public class Financial_Report implements Serializable {
    private int account_Number;
    private float transaction_Amount;
    private LocalDate transaction_Date;

    public Financial_Report() {
    }

    public Financial_Report(int account_Number, float transaction_Amount, LocalDate transaction_Date) {
        this.account_Number = account_Number;
        this.transaction_Amount = transaction_Amount;
        this.transaction_Date = transaction_Date;
    }

    public int getAccount_Number() {
        return account_Number;
    }

    public void setAccount_Number(int account_Number) {
        this.account_Number = account_Number;
    }

    public float getTransaction_Amount() {
        return transaction_Amount;
    }

    public void setTransaction_Amount(float transaction_Amount) {
        this.transaction_Amount = transaction_Amount;
    }

    public LocalDate getTransaction_Date() {
        return transaction_Date;
    }

    public void setTransaction_Date(LocalDate transaction_Date) {
        this.transaction_Date = transaction_Date;
    }

    @Override
    public String toString() {
        return "Financial_Report{" +
                "account_Number=" + account_Number +
                ", transaction_Amount=" + transaction_Amount +
                ", transaction_Date=" + transaction_Date +
                '}';
    }
}

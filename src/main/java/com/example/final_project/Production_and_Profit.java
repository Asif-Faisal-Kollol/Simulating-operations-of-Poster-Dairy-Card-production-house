package com.example.final_project;

import java.io.Serializable;

public class Production_and_Profit implements Serializable {
    private int productID;
    private float productionCost;
    private float revenue;
    private float profit;
    private float budget;

    public Production_and_Profit() {
    }

    public Production_and_Profit(int productID, float productionCost, float revenue, float profit, float budget) {
        this.productID = productID;
        this.productionCost = productionCost;
        this.revenue = revenue;
        this.profit = profit;
        this.budget = budget;
    }

    public float calculateProfit() {
        return revenue - productionCost;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(float productionCost) {
        this.productionCost = productionCost;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getProfit() {
        return calculateProfit();
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Production_and_Profit{" +
                "productID=" + productID +
                ", productionCost=" + productionCost +
                ", revenue=" + revenue +
                ", profit=" + profit +
                ", budget=" + budget +
                '}';
    }
}

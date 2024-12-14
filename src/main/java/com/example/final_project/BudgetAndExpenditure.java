package com.example.final_project;

import java.io.Serializable;

public class BudgetAndExpenditure implements Serializable {
    private float budget_allocation;
    private float expenditure;
    private String description;

    public BudgetAndExpenditure() {
    }

    public BudgetAndExpenditure(float budget_allocation, float expenditure, String description) {
        this.budget_allocation = budget_allocation;
        this.expenditure = expenditure;
        this.description = description;
    }

    public float getBudget_allocation() {
        return budget_allocation;
    }

    public void setBudget_allocation(float budget_allocation) {
        this.budget_allocation = budget_allocation;
    }

    public float getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(float expenditure) {
        this.expenditure = expenditure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BudgetAndExpenditure{" +
                "budget_allocation=" + budget_allocation +
                ", expenditure=" + expenditure +
                ", description='" + description + '\'' +
                '}';
    }
}

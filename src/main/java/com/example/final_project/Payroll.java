package com.example.final_project;

import java.io.Serializable;

public class Payroll implements Serializable {
    private int empId;
    private String empName;
    private String department;
    private int salary;
    private int bonus;
    private int deduction;
    private int totalPay;

    public Payroll() {
    }

    public Payroll(int empId, String empName, String department, int salary, int bonus, int deduction) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.salary = salary;
        this.bonus = bonus;
        this.deduction = deduction;
        this.totalPay = calculateTotalPay();
    }

    public int calculateTotalPay() {
        return salary + bonus - deduction;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getDeduction() {
        return deduction;
    }

    public void setDeduction(int deduction) {
        this.deduction = deduction;
    }

    public int getTotalPay() {
        return calculateTotalPay();
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", deduction=" + deduction +
                ", totalPay=" + totalPay +
                '}';
    }
}

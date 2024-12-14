package com.example.final_project;

public class SalesOfficer extends user {
    public SalesOfficer(int id, String password) {
        super(id, password, "Sales Officer");
    }

    @Override
    public String toString() {
        return "SalesOfficer{" + "id=" + id + ", password='" + password + '\'' + '}';
    }
}


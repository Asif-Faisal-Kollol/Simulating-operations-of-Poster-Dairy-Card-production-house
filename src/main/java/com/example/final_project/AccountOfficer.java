package com.example.final_project;

public class AccountOfficer extends user {
    public AccountOfficer(int id, String password) {
        super(id, password, "Account Officer");
    }

    @Override
    public String toString() {
        return "AccountOfficer{" + "id=" + id + ", password='" + password + '\'' + '}';
    }
}


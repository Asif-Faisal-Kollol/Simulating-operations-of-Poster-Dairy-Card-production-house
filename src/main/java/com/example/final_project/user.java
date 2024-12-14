package com.example.final_project;

import java.io.Serializable;

public abstract class user implements Serializable
{
    protected int id;
    protected String password;
    protected String mainUserType;

    public user() {
    }

    public user(int id, String password, String mainUserType) {
        this.id = id;
        this.password = password;
        this.mainUserType = mainUserType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMainUserType() {
        return mainUserType;
    }

    public void setMainUserType(String mainUserType) {
        this.mainUserType = mainUserType;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", mainUserType='" + mainUserType + '\'' +
                '}';
    }



    public boolean verifyLogin(int enteredId, String enteredPassword, String selectedUserType) {
         return enteredId == id && enteredPassword.equals(password) && selectedUserType.equals(mainUserType);
    }
}

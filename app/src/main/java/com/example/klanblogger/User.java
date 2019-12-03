package com.example.klanblogger;

public class User {
    private String ID;
    private String name;
    private String email;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public User() {
    }
}

package com.juuh.ht;

public class User {
    private int id;
    private String username;
    private String password;
    public User(int id, String username, String password){
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

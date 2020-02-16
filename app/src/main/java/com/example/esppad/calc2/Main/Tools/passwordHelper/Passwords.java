package com.example.esppad.calc2.Main.Tools.passwordHelper;

public class Passwords {
    int id;
    String url;
    String password;

    public Passwords(int id, String url, String password) {
        this.id = id;
        this.url = url;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }
}

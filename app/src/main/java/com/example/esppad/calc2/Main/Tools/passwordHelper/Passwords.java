package com.example.esppad.calc2.Main.Tools.passwordHelper;

public class Passwords {
    int id;
    String url;
    String user;
    String password;

    public Passwords(int id, String url,String user ,String password) {
        this.id = id;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUSer(){
        return user;
    }

    public String getPassword() {
        return password;
    }
}

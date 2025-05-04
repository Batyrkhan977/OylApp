package com.example.oyla;

public class User {
    public String login;
    public String password;
    public String username;
    public String key;
    public User(){

    }
    public User(String login, String password, String username, String key){
        this.username = username;
        this.login = login;
        this.password = password;
        this.key = key;
    }
    public String getUsername() {
        return username;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

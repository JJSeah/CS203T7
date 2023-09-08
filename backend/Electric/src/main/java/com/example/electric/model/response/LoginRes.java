package com.example.electric.model.response;

public class LoginRes {
    private String email;
    private String token;
    private long id;

    public LoginRes(long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public long getID() {
        return id;
    }
}

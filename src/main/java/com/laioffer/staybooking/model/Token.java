package com.laioffer.staybooking.model;

//convert string(Jwt) to json
public class Token {
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
package com.library.model;

public class AuthResponse {

    private String userName;
    private String jwtToken;

    public AuthResponse(String userName, String jwtToken) {
        super();
        this.userName = userName;
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}

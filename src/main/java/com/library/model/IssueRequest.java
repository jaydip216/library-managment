package com.library.model;

import javax.validation.constraints.NotNull;

public class IssueRequest {

    @NotNull
    private String isbn;
    @NotNull
    private String userName;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

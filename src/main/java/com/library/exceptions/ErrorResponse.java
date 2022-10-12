package com.library.exceptions;

import java.util.Date;

public class ErrorResponse {
    public String message;
    public String errorCode;
    public Date timeStamp;

    public ErrorResponse(String message, String errorCode, Date date) {
        super();
        this.message = message;
        this.errorCode = errorCode;
        this.timeStamp = date;
    }

}

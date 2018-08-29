package com.mcctest.serverclient.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hasan on 5/21/17.
 */

public class ErrorResponse {

    private int httpStatus;

    @SerializedName("errorCode")
    private String errorCode;

    @SerializedName("message")
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int httpStatus , String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

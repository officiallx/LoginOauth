package com.example.postloginoauth.models;

/**
 * Created by Bleeding Rain on 4/6/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notice extends Content {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

}
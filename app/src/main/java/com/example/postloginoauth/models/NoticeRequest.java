package com.example.postloginoauth.models;

import java.util.ArrayList;


public class NoticeRequest {

    ArrayList<NoticeRequest> noticeRequests = new ArrayList<>();
    public NoticeRequest() {
    }

    public NoticeRequest(ArrayList<NoticeRequest> noticeRequests) {
        this.noticeRequests = noticeRequests;
    }
}

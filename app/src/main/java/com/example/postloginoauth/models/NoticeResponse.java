package com.example.postloginoauth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoticeResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("lastModified")
    @Expose
    private Integer lastModified;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("localDate")
    @Expose
    private String localDate;
    @SerializedName("localEndDate")
    @Expose
    private Object localEndDate;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("noticeType")
    @Expose
    private String noticeType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("new")
    @Expose
    private Boolean _new;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getLastModified() {
        return lastModified;
    }

    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public Object getLocalEndDate() {
        return localEndDate;
    }

    public void setLocalEndDate(Object localEndDate) {
        this.localEndDate = localEndDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getNew() {
        return _new;
    }

    public void setNew(Boolean _new) {
        this._new = _new;
    }

    @Override
    public String toString() {
        return "NoticeResponse{" +
                "id=" + id +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", title='" + title + '\'' +
                ", localDate='" + localDate + '\'' +
                ", localEndDate=" + localEndDate +
                ", content='" + content + '\'' +
                ", noticeType='" + noticeType + '\'' +
                ", status='" + status + '\'' +
                ", _new=" + _new +
                '}';
    }
}
package com.example.actividaddeaprendizaje.beans;

public class ResponseAPI {
    private int code;
    private String status;
    private String etag;
    private DataAPI data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public DataAPI getData() {
        return data;
    }

    public void setData(DataAPI data) {
        this.data = data;
    }
}

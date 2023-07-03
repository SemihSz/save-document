package com.application.document.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Semih, 2.07.2023
 */
@Getter
@Setter
public class
RestResponse<T> implements Serializable {

    private static final long serialVersionId = 1L;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("response")
    private T response;

    public RestResponse(Long timestamp, int responseCode, T response) {
        this.timestamp = timestamp;
        this.responseCode = responseCode;
        this.response = response;
    }

    public RestResponse(int responseCode, T response) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
        this.response = response;
    }

    public RestResponse(int responseCode, Exception e) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
    }

    public RestResponse(int responseCode) {
        this.timestamp = new Date().getTime();
        this.responseCode = responseCode;
    }

    public static long getSerialVersionId() {
        return serialVersionId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

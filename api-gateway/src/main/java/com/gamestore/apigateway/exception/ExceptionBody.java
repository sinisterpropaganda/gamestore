/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

/**
 *
 * @author qbuser
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionBody {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ExceptionBody{" + "timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message=" + message + ", path=" + path + '}';
    }
}

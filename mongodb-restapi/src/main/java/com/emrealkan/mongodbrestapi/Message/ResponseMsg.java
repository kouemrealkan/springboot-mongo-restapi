package com.emrealkan.mongodbrestapi.Message;

import com.emrealkan.mongodbrestapi.Entity.Classroom;
import com.emrealkan.mongodbrestapi.Entity.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResponseMsg {


    private String message;
    private String url;
    private String error = "";

    public ResponseMsg(String message,String url){
        this.message = message;
        this.url = url;
    }


    public ResponseMsg(String message,String url,String error){
        this.message = message;
        this.error = error;
    }






    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}

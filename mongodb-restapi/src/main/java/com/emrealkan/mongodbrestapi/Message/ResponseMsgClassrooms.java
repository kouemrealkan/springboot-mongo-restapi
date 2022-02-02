package com.emrealkan.mongodbrestapi.Message;

import com.emrealkan.mongodbrestapi.Entity.Classroom;

import java.util.List;

public class ResponseMsgClassrooms extends ResponseMsg{

    private List<Classroom> classrooms;

    public ResponseMsgClassrooms(String message,String url,List<Classroom> classrooms){
        super(message,url);
        this.classrooms = classrooms;
    }

    public ResponseMsgClassrooms(String message, String url) {
        this(message, url, List.of());
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}

package com.emrealkan.mongodbrestapi.Message;

import com.emrealkan.mongodbrestapi.Entity.Student;

import java.util.List;

public class ResponseMsgStudents extends ResponseMsg {
    private List<Student> students;


   public ResponseMsgStudents(String message,String url,List<Student> students){
       super(message,url);
       this.students = students;

   }


    public ResponseMsgStudents(String message, String url) {
        this(message, url, List.of());
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

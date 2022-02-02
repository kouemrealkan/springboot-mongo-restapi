package com.emrealkan.mongodbrestapi.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "classroom")
public class Classroom {
    @Id
    private String id;

    @Indexed(unique = true)
    private String classRoomCode;
    private int capacity;
    private List<Student> studentList;

    public Classroom(String classRoomCode,int capacity, List<Student> studentList) {
        this.classRoomCode = classRoomCode;
        this.capacity = capacity;
        this.studentList = studentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}

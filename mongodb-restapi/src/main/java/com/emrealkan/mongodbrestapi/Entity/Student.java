package com.emrealkan.mongodbrestapi.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private String lastName;

    @Indexed(unique = true)
    private String studentNumber;
    private String address;
    private String classCode;
    private Date birthDate;

    public Student(String name, String lastName, String studentNumber, String address, String classCode, Date birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.address = address;
        this.classCode = classCode;
        this.birthDate = birthDate;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

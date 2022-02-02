package com.emrealkan.mongodbrestapi.Service;

import com.emrealkan.mongodbrestapi.Entity.Student;
import com.emrealkan.mongodbrestapi.Exception.CustomException;
import com.emrealkan.mongodbrestapi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;

    public StudentService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id){
        return studentRepository.findById(id);
    }


    public List<Student> findStudentByName(String name){
        Query query = new Query()
                .addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query,Student.class);
    }

   public List<Student> getStudentWithClassQuery(){
       Query query = new Query()
               .addCriteria(Criteria.where("classCode").is("1040"))
               .with(Sort.by(Sort.Order.desc("birthDate")));

       return mongoTemplate.find(query,Student.class);
   }


   public List<Student> getStudentWithNameQuery(){
       Query query = new Query()
               .addCriteria(Criteria.where("name").is("Yunus"))
               .addCriteria(Criteria.where(("lastName")).is("Alkan"));
        return mongoTemplate.find(query,Student.class);
    }

    public Student updateStudent(String id,Student student) throws CustomException{
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()){
            throw new CustomException("404", "Can not find a student for updating with id = " + id);
        }

        Student _student = studentOptional.get();

        _student.setName(student.getName());
        _student.setLastName(student.getLastName());
        _student.setStudentNumber(student.getStudentNumber());
        _student.setAddress(student.getAddress());
        _student.setClassCode(student.getClassCode());

        studentRepository.save(_student);
        return  _student;


    }

    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }


}

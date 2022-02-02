package com.emrealkan.mongodbrestapi.Rest;


import com.emrealkan.mongodbrestapi.Entity.Classroom;
import com.emrealkan.mongodbrestapi.Entity.Student;
import com.emrealkan.mongodbrestapi.Exception.CustomException;
import com.emrealkan.mongodbrestapi.Message.ResponseMsg;
import com.emrealkan.mongodbrestapi.Message.ResponseMsgClassrooms;
import com.emrealkan.mongodbrestapi.Message.ResponseMsgStudents;
import com.emrealkan.mongodbrestapi.Service.ClassroomService;
import com.emrealkan.mongodbrestapi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class WebStudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<ResponseMsg> getAllStudents(HttpServletRequest request){
        try {
            List<Student> students = studentService.getAllStudents();
            System.out.println("TOplam sayı :" + students.size());
            String msg = "Success !";
            return new ResponseEntity<>(new ResponseMsgStudents(msg, request.getRequestURI(), students), HttpStatus.OK);
        }catch (Exception e){
            String message = "Failed !";
            return new ResponseEntity<>(new ResponseMsg(message,request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/student-with-name-query")
    public ResponseEntity<ResponseMsg> getStudentNameQuery(HttpServletRequest request){
        try {
            List<Student> students = studentService.getStudentWithNameQuery();
            String msg = "Success !";
            return new ResponseEntity<>(new ResponseMsgStudents(msg,request.getRequestURI(),students),HttpStatus.OK);
        }catch (Exception e){
            String message = "Failed !";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/student-with-class-query")
    public ResponseEntity<ResponseMsg> getStudentClassQuery(HttpServletRequest request){
        try {
            List<Student> students = studentService.getStudentWithClassQuery();
            String msg = "Success !";
            return new ResponseEntity<>(new ResponseMsgStudents(msg,request.getRequestURI(),students),HttpStatus.OK);
        }catch (Exception e){
            String message = "Failed !";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<ResponseMsg> findStudentByName(@PathVariable String name, HttpServletRequest request){
        try {
            List<Student> students = studentService.findStudentByName(name.substring(0,1).toUpperCase()+ name.substring(1));
            String msg = "Success !";
            return new ResponseEntity<>(new ResponseMsgStudents(msg,request.getRequestURI(),students),HttpStatus.OK);
        }catch (Exception e){
            String message = "Failed !";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/student-getone/{id}")
    public ResponseEntity<ResponseMsg> getStudentByIdVal(@PathVariable String id, HttpServletRequest request){
        try {
            Optional<Student> student = studentService.getStudentById(id);
            if(student.isPresent()){
                String message = "Student Found !";
                return new ResponseEntity<>(new ResponseMsgStudents(message, request.getRequestURI(), List.of(student.get())), HttpStatus.OK);
            }else{
                String message = "Student Not Found !";
                return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                        "NOT FOUND"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            String message = "Failed to fetch student  !";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/create")
    public ResponseEntity<ResponseMsg> saveStudent(@RequestBody Student student, HttpServletRequest request) {
        try {
            // save to MongoDB database
            Student _student = studentService.saveStudent(student);

            String message = "Upload Successfully a Student to MongoDB with id = " + _student.getId();
            return new ResponseEntity<>(new ResponseMsgStudents(message, request.getRequestURI(),
                    List.of(student)), HttpStatus.OK);
        }catch(Exception e) {
            String message = "Can NOT upload  a Student to MongoDB database";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<ResponseMsg> updateStudentById(@PathVariable String id,@RequestBody Student student,HttpServletRequest request){
        try {
            // update a customer to MongoDB
            Student _student = studentService.updateStudent(id, student);
            String message = "Successfully Update a Student to MongoDB with id = " + id;

            return new ResponseEntity<ResponseMsg> (new ResponseMsgStudents(message, request.getRequestURI(),
                    List.of(_student)), HttpStatus.OK);
        } catch (CustomException ce) {
            String message = "Can NOT update to MongoDB a Student with id = " + id;
            return new ResponseEntity<ResponseMsg> (new ResponseMsg(message, request.getRequestURI(),
                    ce.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String message = "Can NOT update to MongoDB a Student with id = " + id;
            return new ResponseEntity<ResponseMsg> (new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")

    public ResponseEntity<ResponseMsg> deleteStudentById(@PathVariable String id,HttpServletRequest request){
        try {
            // delete a Customer from MongoDB database using ID
            studentService.deleteStudent(id);

            String message = "Successfully delete a Student from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMsg>(new ResponseMsgStudents(message, request.getRequestURI()), HttpStatus.OK);
        } catch(Exception e) {
            String message = "Can Not delete a Student from MongoDB database with id = " + id;
            return new ResponseEntity<ResponseMsg>(new ResponseMsg(message, request.getRequestURI(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}

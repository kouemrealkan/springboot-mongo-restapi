package com.emrealkan.mongodbrestapi.Rest;

import com.emrealkan.mongodbrestapi.Entity.Classroom;
import com.emrealkan.mongodbrestapi.Entity.Student;
import com.emrealkan.mongodbrestapi.Message.ResponseMsg;
import com.emrealkan.mongodbrestapi.Message.ResponseMsgClassrooms;
import com.emrealkan.mongodbrestapi.Message.ResponseMsgStudents;
import com.emrealkan.mongodbrestapi.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
public class WebClassroomController {
    @Autowired
    ClassroomService classroomService;

    @GetMapping("/all")
    public ResponseEntity<ResponseMsg> getAllClassrooms(HttpServletRequest request){
        try {
            List<Classroom> classrooms = classroomService.getAllClassrooms();
            String msg = "Success !";
            return new ResponseEntity<>(new ResponseMsgClassrooms(msg,request.getRequestURI(),classrooms),HttpStatus.OK);
        }catch (Exception e){
            String message = "Failed !";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMsg> saveClassroom(@RequestBody Classroom classroom, HttpServletRequest request) {
        try {
            // save to MongoDB database
            Classroom _classroom = classroomService.saveClassroom(classroom);

            String message = "Upload Successfully a Classroom to MongoDB with id = " + _classroom.getId();
            return new ResponseEntity<>(new ResponseMsgClassrooms(message, request.getRequestURI(),
                    List.of(classroom)), HttpStatus.OK);
        }catch(Exception e) {
            String message = "Can NOT upload  a Classroom to MongoDB database";
            return new ResponseEntity<>(new ResponseMsg(message, request.getRequestURI(),
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

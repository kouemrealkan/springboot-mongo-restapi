package com.emrealkan.mongodbrestapi.Service;


import com.emrealkan.mongodbrestapi.Entity.Classroom;
import com.emrealkan.mongodbrestapi.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom saveClassroom(Classroom classroom){
        return classroomRepository.save(classroom);
    }

    public List<Classroom> getAllClassrooms(){
        return classroomRepository.findAll();
    }
}

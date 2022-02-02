package com.emrealkan.mongodbrestapi.Repository;

import com.emrealkan.mongodbrestapi.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {


}

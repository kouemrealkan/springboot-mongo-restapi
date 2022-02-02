package com.emrealkan.mongodbrestapi.Repository;

import com.emrealkan.mongodbrestapi.Entity.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassroomRepository extends MongoRepository<Classroom,String> {

}

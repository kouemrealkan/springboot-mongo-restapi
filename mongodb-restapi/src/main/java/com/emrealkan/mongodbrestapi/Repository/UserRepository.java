package com.emrealkan.mongodbrestapi.Repository;

import com.emrealkan.mongodbrestapi.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String> {
    User findByUserName(String userName);
}

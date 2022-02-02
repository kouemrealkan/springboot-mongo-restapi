package com.emrealkan.mongodbrestapi.Service;
import com.emrealkan.mongodbrestapi.Entity.User;
import com.emrealkan.mongodbrestapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User foundedUser = userRepository.findByUserName(username);
        if(foundedUser == null)
            return null;
        String name = foundedUser.getUserName();
        String pwd = foundedUser.getPassword();
        return new org.springframework.security.core.userdetails.User(name,pwd,new ArrayList<>());
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
}

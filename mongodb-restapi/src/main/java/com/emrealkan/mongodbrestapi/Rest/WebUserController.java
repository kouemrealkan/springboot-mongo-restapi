package com.emrealkan.mongodbrestapi.Rest;

import com.emrealkan.mongodbrestapi.Entity.AuthenticationRequest;
import com.emrealkan.mongodbrestapi.Entity.AuthenticationResponse;
import com.emrealkan.mongodbrestapi.Entity.User;
import com.emrealkan.mongodbrestapi.Repository.UserRepository;
import com.emrealkan.mongodbrestapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class WebUserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    private ResponseEntity<AuthenticationResponse> getAllStudents(HttpServletRequest request){
      try {
          List<User> users = userService.getAllUsers();
          String msg = "Success !";
          return new ResponseEntity<>(new AuthenticationResponse(msg,users), HttpStatus.OK);
      }catch (Exception e){
          return ResponseEntity.ok(new AuthenticationResponse("Error Fetching User"));
      }
    }



    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@RequestBody AuthenticationRequest authenticationRequest){
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        }catch (Exception e){
            return ResponseEntity.ok(new AuthenticationResponse("Error Registering User"));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Successful Register User" + userName));
    }


   @PostMapping("/login")
    private ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest){

       String userName = authenticationRequest.getUserName();
       String password = authenticationRequest.getPassword();

       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password,null));
       }catch (Exception e){
           return new ResponseEntity<>(new AuthenticationResponse("Error When Login User"), HttpStatus.NOT_FOUND);
       }
       return ResponseEntity.ok(new AuthenticationResponse("Successful Login User" + userName));
    }




}

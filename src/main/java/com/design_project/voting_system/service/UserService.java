package com.design_project.voting_system.service;

import com.design_project.voting_system.model.User;
import com.design_project.voting_system.repository.UserRepository;
import org.apache.tomcat.jni.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String addUser(User user) {
        userRepository.save(user);
        return "User added";
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted";
    }


    public Object validateNIC(String NICnumber) {
        User user = userRepository.findByNicNumber(NICnumber);
        if (user != null) {
            if (!user.isHasVoted()) {

                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    public Object validateFingerPrint(String NICnumber, String fingerPrint) {
        User user = userRepository.findByNicNumber(NICnumber);

        if (fingerPrint.equals(user.getFingerPrint())) {
            user.setHasVoted(true);
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}

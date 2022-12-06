package com.example.springstart.service;

import com.example.springstart.entity.UserEntity;
import com.example.springstart.exception.UserAlreadyExistException;
import com.example.springstart.exception.UserNotFoundException;

import com.example.springstart.model.User;
import com.example.springstart.repozitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername())!=null){
            throw new UserAlreadyExistException("User has exist");
        }
       return userRepo.save(user);
    }
    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user=userRepo.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }
    public  Long deleteUser(Long id){
        userRepo.deleteById(id);
        return id;
    }
}

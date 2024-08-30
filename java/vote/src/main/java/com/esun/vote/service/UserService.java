package com.esun.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.model.Users;
import com.esun.vote.respository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ObjectMapper objMapper;

    public Users createUser(String requestBody) {
        if (requestBody.isBlank() || requestBody.isEmpty()) {
            return null;
        }
        try {
            Users newUser = objMapper.readValue(requestBody, Users.class);
            return userRepo.save(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

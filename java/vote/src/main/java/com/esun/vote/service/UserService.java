package com.esun.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.respository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
}

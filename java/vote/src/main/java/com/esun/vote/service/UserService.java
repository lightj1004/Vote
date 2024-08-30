package com.esun.vote.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder pwdEncoder;

    public Users createUser(String requestBody) {
        // 檢查輸入資料是否為空
        if (requestBody.isBlank() || requestBody.isEmpty()) {
            return null;
        }
        try {
            Users newUser = objMapper.readValue(requestBody, Users.class);
            // 密碼加密
            newUser.setPassword(pwdEncoder.encode(newUser.getPassword()));
            return userRepo.save(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users login(String requestBody) {
        JSONObject loginData = new JSONObject(requestBody);
        String username = loginData.getString("username");
        String password = loginData.getString("password");

        // 檢查輸入資料是否為空
        if (username.isBlank() || username.isEmpty() || password.isEmpty() || password.isBlank()) {
            return null;
        }

        // 搜尋使用者
        Optional<Users> result = userRepo.findByUsername(username);

        // 檢查使用者是否存在
        if (!result.isPresent()) {
            return null;
        } else {
            Users user = result.get();

            // 比對密碼
            if (pwdEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
    }
}

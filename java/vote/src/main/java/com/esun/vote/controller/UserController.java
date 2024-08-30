package com.esun.vote.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.model.Users;
import com.esun.vote.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public String createUser(@RequestBody String requestBody) {
        JSONObject responseBody = new JSONObject();
        Users result = userService.createUser(requestBody);
        if (result == null) {
            responseBody.put("success", false);
            responseBody.put("message", "註冊失敗");
        } else {
            responseBody.put("success", true);
            responseBody.put("message", "註冊成功");
        }
        return responseBody.toString();
    }

}

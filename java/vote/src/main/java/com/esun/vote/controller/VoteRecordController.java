package com.esun.vote.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.model.RecordCount;
import com.esun.vote.service.VoteRecordService;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class VoteRecordController {
    @Autowired
    private VoteRecordService recordService;

    @GetMapping("/")
    public String getRecordCount() {
        JSONObject responseBody = new JSONObject();
        List<RecordCount> result = recordService.getRecordCounts();
        if (result.size() != 0 && !result.isEmpty()) {
            responseBody.put("success", true);
            responseBody.put("counts", result);
            responseBody.put("message", "票數查詢成功");
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "票數查詢失敗");
        }
        return responseBody.toString();
    }

    @GetMapping("/check/{username}")
    public String checkUserVoted(@PathVariable String username) {
        JSONObject responseBody = new JSONObject();
        if (recordService.checkUserVoted(username)) {
            responseBody.put("success", true);
            responseBody.put("message", "已投票");
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "未投票");
        }
        return responseBody.toString();
    }

    @PostMapping("/vote/{username}")
    public String doVote(@RequestBody String requestBody, @PathVariable String username) {
        JSONObject responseBody = new JSONObject();
        String[] arr = requestBody.replace('"', ' ').replace('[', ' ').replace(']', ' ').trim().split(" , ");
        if(recordService.doVote(arr, username)){
            responseBody.put("success",true);
            responseBody.put("message","投票成功");
        }else{
            responseBody.put("success",false);
            responseBody.put("message","投票失敗");
        }
        return responseBody.toString();
    }

}

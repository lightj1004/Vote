package com.esun.vote.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.model.VoteElement;
import com.esun.vote.service.VoteElementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/element")
public class VoteElementController {
    @Autowired
    private VoteElementService elementService;

    @GetMapping("/")
    public String getAllElement() {
        JSONObject responseBody = new JSONObject();
        List<VoteElement> allElements = elementService.findAllElements();
        if (!allElements.isEmpty() && allElements.size() != 0) {
            responseBody.put("elements", allElements);
            responseBody.put("message", "查詢成功");
            responseBody.put("success", true);
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "查無資料");
        }
        return responseBody.toString();
    }

    @PostMapping("/")
    public String createElement(@RequestBody String requestBody) {
        JSONObject responseBody = new JSONObject();
        VoteElement result = elementService.createElement(requestBody);
        if (result == null) {
            responseBody.put("message", "新增失敗");
            responseBody.put("success", false);
        } else {
            responseBody.put("message", "新增成功");
            responseBody.put("success", true);
        }

        return responseBody.toString();
    }

    @PutMapping("/{id}")
    public String putMethodName(@PathVariable Integer id, @RequestBody String requestBody) {
        JSONObject responseBody = new JSONObject();
        VoteElement result = elementService.updateElement(id, requestBody);
        if (result == null) {
            responseBody.put("message", "修改失敗");
            responseBody.put("success", false);
        } else {
            responseBody.put("message", "修改成功");
            responseBody.put("success", true);
        }
        return responseBody.toString();
    }

}

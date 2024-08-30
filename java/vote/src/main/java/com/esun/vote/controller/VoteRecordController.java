package com.esun.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.service.VoteRecordService;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class VoteRecordController {
    @Autowired
    private VoteRecordService recordService;
}

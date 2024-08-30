package com.esun.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.service.VoteRecordService;

@RestController
@RequestMapping("/api/record")
public class VoteRecordController {
    @Autowired
    private VoteRecordService recordService;
}

package com.esun.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.respository.VoteRecordRepository;

@Service
public class VoteRecordService {
    @Autowired
    private VoteRecordRepository recordRepo;
}

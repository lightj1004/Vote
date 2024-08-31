package com.esun.vote.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esun.vote.model.RecordCount;
import com.esun.vote.model.VoteRecord;
import com.esun.vote.respository.VoteRecordRepository;

@Service
public class VoteRecordService {
    @Autowired
    private VoteRecordRepository recordRepo;

    @Transactional(readOnly = true)
    public List<RecordCount> getRecordCounts() {
        List<Object[]> results = recordRepo.recordCounts(); // 获取原始数据
        return results.stream()
                .map(result -> new RecordCount(
                        (Integer) result[0], // 将列映射到 DTO 构造函数
                        ((Number) result[2]).longValue() // 强制转换为 Long
                ))
                .collect(Collectors.toList());
    }

    public Boolean checkUserVoted(String username) {
        List<VoteRecord> result = recordRepo.findByUsername(username);
        if (!result.isEmpty() && result.size() != 0) {
            return true;
        }
        return false;
    }

    public Boolean doVote(String[] arr, String username) {
        VoteRecord record = null;
        int i = 0;
        for (String element : arr) {
            record = new VoteRecord();
            record.setUsername(username);
            record.setElement(Integer.parseInt(element));
            recordRepo.save(record);
            i++;
        }
        if (i == arr.length) {
            return true;
        }
        return false;
    }
}

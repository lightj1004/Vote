package com.esun.vote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esun.vote.model.VoteElement;
import com.esun.vote.respository.VoteElementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VoteElementService {
    @Autowired
    private VoteElementRepository elementRepo;

    @Autowired
    private ObjectMapper objMapper;

    public List<VoteElement> findAllElements() {
        return elementRepo.findAll();
    }

    public VoteElement createElement(String requestBody) {
        try {
            VoteElement newElement = objMapper.readValue(requestBody, VoteElement.class);
            return elementRepo.save(newElement);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public VoteElement updateElement(Integer id, String requestBody) {
        Optional<VoteElement> op = elementRepo.findById(id);
        if (!op.isPresent()) {
            return null;
        }
        try {
            VoteElement newElement = objMapper.readValue(requestBody, VoteElement.class);
            return elementRepo.save(newElement);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

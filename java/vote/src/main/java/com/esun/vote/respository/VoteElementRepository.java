package com.esun.vote.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.esun.vote.model.VoteElement;

@Repository
public interface VoteElementRepository extends JpaRepository<VoteElement, Integer> {

    @Procedure(name = "GetElementCounts")
    public List<?> getElementCounts();
}

package com.esun.vote.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esun.vote.model.VoteElement;

@Repository
public interface VoteElementRepository extends JpaRepository<VoteElement, Integer> {

}

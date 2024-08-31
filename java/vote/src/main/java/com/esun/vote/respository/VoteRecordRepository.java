package com.esun.vote.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esun.vote.model.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {

    @Query(value = "select e.id, e.name, coalesce(r.cnt,0) as cnt from vote_element e left  join (select element ,count(*) as cnt from vote_record group by element) as r on e.id = r.element", nativeQuery = true)
    public List<Object[]> recordCounts();
}

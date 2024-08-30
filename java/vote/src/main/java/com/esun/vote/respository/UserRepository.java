package com.esun.vote.respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esun.vote.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

}

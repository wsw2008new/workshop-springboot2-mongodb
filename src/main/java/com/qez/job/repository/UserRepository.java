package com.qez.job.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qez.job.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}

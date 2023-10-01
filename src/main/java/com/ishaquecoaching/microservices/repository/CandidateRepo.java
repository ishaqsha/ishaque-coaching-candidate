package com.ishaquecoaching.microservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.ishaquecoaching.microservices.model.Candidate;
import java.util.List;

@Repository
public interface CandidateRepo extends MongoRepository<Candidate, Long> {

	public List<Candidate> findByEmailOrMobile(String email,String mobile);
}

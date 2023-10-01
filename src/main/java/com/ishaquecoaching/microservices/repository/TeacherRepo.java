package com.ishaquecoaching.microservices.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ishaquecoaching.microservices.model.Candidate;
import com.ishaquecoaching.microservices.model.Teacher;


@Repository
public interface TeacherRepo extends MongoRepository<Teacher, Long>{

}

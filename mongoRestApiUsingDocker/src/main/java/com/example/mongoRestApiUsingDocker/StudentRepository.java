package com.example.mongoRestApiUsingDocker;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface StudentRepository
        extends MongoRepository<Student, String> {
   //Optional<Student> findStudentByEmail(String email);





}

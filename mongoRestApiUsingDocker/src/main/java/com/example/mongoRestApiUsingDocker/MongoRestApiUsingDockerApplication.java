package com.example.mongoRestApiUsingDocker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoRestApiUsingDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoRestApiUsingDockerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "United Kingdom",
                    "Nottingham",
                    "NG9"
            );
            String email = "ahmedofficial.2211@gmail.com";
            Student student = new Student(
                    "Kawsar",
                    "Ahmed",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            //repository.insert(student);
            checkAndInsertStudent(repository, mongoTemplate, email, student);
        };
    }

    private void checkAndInsertStudent(StudentRepository repository,
                                       MongoTemplate mongoTemplate,
                                       String email,
                                       Student student) {
        if (!email.toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Invalid email: " + email + ". Must end with @gmail.com");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("Found multiple students with email: " + email);
        }

        if (students.isEmpty()) {
            System.out.println("Inserting student: " + student);
            repository.insert(student);
        } else {
            System.out.println(student + " already exists");
        }
    }
}

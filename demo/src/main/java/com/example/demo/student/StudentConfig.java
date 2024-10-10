package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Orel = new Student(
                    "Orel",
                    "Orel@gmail.com",
                    LocalDate.of(1997, Month.NOVEMBER,16)
            );

            Student Alex = new Student(
                    "Alex",
                    "Alex@gmail.com",
                    LocalDate.of(2000, Month.NOVEMBER,16)
            );
            repository.saveAll(List.of(Orel, Alex));
        };

    };
}


package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student("Mariam", "Mariam.rodolfes@gmail.com", LocalDate.of(2000, Month.JANUARY, 13));
            Student alex = new Student("alex", "alex@gmail.com", LocalDate.of(2001, Month.DECEMBER, 25));

            repository.saveAll(List.of(mariam, alex));
        };
    }
}

package se.lexicon.week40_taskmanagement_springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;
import se.lexicon.week40_taskmanagement_springjpa.repository.RoleRepository;
import se.lexicon.week40_taskmanagement_springjpa.repository.TaskRepository;
import se.lexicon.week40_taskmanagement_springjpa.startup.RoleDataLoader;
import se.lexicon.week40_taskmanagement_springjpa.startup.TaskDataLoader;

import java.time.LocalDate;

@SpringBootApplication
public class Week40TaskManagementSpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week40TaskManagementSpringJpaApplication.class, args);
    }
}
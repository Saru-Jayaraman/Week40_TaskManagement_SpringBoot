package se.lexicon.week40_taskmanagement_springjpa.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;
import se.lexicon.week40_taskmanagement_springjpa.repository.TaskRepository;

import java.time.LocalDate;

@Component
public class TaskDataLoader implements CommandLineRunner {
    TaskRepository taskRepository;
    Task task1, task2, task3;

    @Autowired
    public TaskDataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        task1 = new Task("Fix light", null, LocalDate.now().plusDays(10), false);
        task2 = new Task("Fix horn", null, LocalDate.now().plusDays(5), false);
        task3 = new Task(null, null, LocalDate.now().minusDays(1), false);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
    }
}

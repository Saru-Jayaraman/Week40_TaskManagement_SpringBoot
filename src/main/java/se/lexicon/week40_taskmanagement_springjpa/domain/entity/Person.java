package se.lexicon.week40_taskmanagement_springjpa.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Setter private String name;

    @OneToMany(mappedBy = "person")
    @Setter private List<Task> taskList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "email")
    @Setter private User user;

    public Person(String name) {
        this.name = name;
    }

    public void addTask(Task... tasks) {
        if(Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks are either null/empty...");
        for(Task eachTask : tasks) {
            if(eachTask.getPerson() == null)
                eachTask.setPerson(this);
            this.taskList.add(eachTask);
        }
    }

    public void removeTask(Task... tasks) {
        if(Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks are either null/empty...");
        for(Task eachTask : tasks) {
            if (this.taskList.remove(eachTask) && eachTask.getPerson() == this) {
                eachTask.setPerson(null);
            }
        }
    }
}
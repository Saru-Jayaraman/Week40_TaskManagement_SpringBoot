package se.lexicon.week40_taskmanagement_springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    //Find Persons who have no tasks
    @Query("select p from Person p where size(p.taskList) = 0")
    List<Person> findPersonWithNoTasks();
}

package se.lexicon.week40_taskmanagement_springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //Find Persons who have no tasks
    @Query("select p from Person p where size(p.taskList) = 0")
    List<Person> findPersonsWithNoTasks();

    //Find Person by User email
    Optional<Person> findByUser_Email(String email);
}

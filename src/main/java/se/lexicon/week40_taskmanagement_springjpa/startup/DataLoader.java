package se.lexicon.week40_taskmanagement_springjpa.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.week40_taskmanagement_springjpa.converter.RoleConverter;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.*;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;
import se.lexicon.week40_taskmanagement_springjpa.repository.PersonRepository;
import se.lexicon.week40_taskmanagement_springjpa.repository.TaskRepository;
import se.lexicon.week40_taskmanagement_springjpa.service.PersonService;
import se.lexicon.week40_taskmanagement_springjpa.service.RoleService;
import se.lexicon.week40_taskmanagement_springjpa.service.UserService;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    PersonService personService;
    PersonRepository personRepository;
    TaskRepository taskRepository;
    RoleConverter roleConverter;

    @Autowired
    public DataLoader(RoleService roleService, UserService userService, PersonService personService, PersonRepository personRepository, TaskRepository taskRepository, RoleConverter roleConverter) {
        this.roleService = roleService;
        this.userService = userService;
        this.personService = personService;
        this.personRepository = personRepository;
        this.taskRepository = taskRepository;
        this.roleConverter = roleConverter;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------ROLE SERVICE------------------------------");
        System.out.println("----------------------------------SAVE----------------------------------");
        RoleDTOFormSave roleDTOForm1 = new RoleDTOFormSave("ADMIN");
        RoleDTOFormSave roleDTOForm2 = new RoleDTOFormSave("USER");
        RoleDTOFormSave roleDTOForm3 = new RoleDTOFormSave("GUEST");

        RoleDTOFormView roleDTOView1 = roleService.saveRole(roleDTOForm1);
        roleService.saveRole(roleDTOForm2);
        roleService.saveRole(roleDTOForm3);

        System.out.println("--------------------------------FIND ALL--------------------------------");
        List<RoleDTOFormView> allRolesFormView = roleService.getAll();
        allRolesFormView.forEach(System.out::println);

        System.out.println("----------------------------FIND BY ROLE NAME---------------------------");
        RoleDTOFormView findByRoleName = roleService.findByName(roleDTOView1.getName());
        System.out.println(findByRoleName);
        System.out.println();

        List<RoleDTOForm> allRolesForm = new ArrayList<>();
        for(RoleDTOFormView eachDTO : allRolesFormView) {
            allRolesForm.add(roleConverter.toRoleDTOForm(eachDTO));
        }

        System.out.println("------------------------------USER SERVICE------------------------------");
        System.out.println("--------------------------------REGISTER--------------------------------");
        UserDTOForm userDTOForm1 = new UserDTOForm("test1@gmail.com", "test1", Set.of(allRolesForm.get(1), allRolesForm.get(2)));
        UserDTOForm userDTOForm2 = new UserDTOForm("test2@gmail.com", "test2", Set.of(allRolesForm.get(2)));

        UserDTOView userDTOView1 = userService.register(userDTOForm1);
        UserDTOView userDTOView2 = userService.register(userDTOForm2);
        System.out.println(userDTOView1);
        System.out.println(userDTOView2);

        System.out.println("-----------------------------UPDATE PASSWORD----------------------------");
        userService.updatePassword(userDTOForm1.getEmail(), "test11");

        System.out.println("-----------------------------DISABLE EXPIRED----------------------------");
        userService.disableByEmail(userDTOForm1.getEmail());

        System.out.println("-------------------------------GET BY EMAIL-----------------------------");
        UserDTOView getRoleByEmail = userService.getByEmail(userDTOForm1.getEmail());
        System.out.println(getRoleByEmail);

        System.out.println("-----------------------------ENABLE EXPIRED-----------------------------");
        userService.enableByEmail(userDTOForm1.getEmail());
        System.out.println();

        System.out.println("-----------------------------PERSON SERVICE-----------------------------");
        System.out.println("----------------------------------SAVE----------------------------------");
        PersonDTOFormSave person1 = new PersonDTOFormSave("Person1", userDTOForm1);
        PersonDTOFormSave person2 = new PersonDTOFormSave("Person2", userDTOForm2);

        PersonDTOFormView personDTOView1 = personService.savePerson(person1);
        PersonDTOFormView personDTOView2 = personService.savePerson(person2);
        System.out.println(personDTOView1);
        System.out.println(personDTOView2);

        System.out.println("--------------------------------FIND ALL--------------------------------");
        List<PersonDTOFormView> allPersonsFormView =  personService.getAll();
        allPersonsFormView.forEach(System.out::println);

        System.out.println("-----------------------FIND PERSONS WITH NO TASKS-----------------------");
        List<PersonDTOFormView> allPersonsWithoutTasks =  personService.findPersonsWithNoTasks();
        allPersonsWithoutTasks.forEach(System.out::println);

        System.out.println("-----------------------FIND PERSON WITH USER EMAIL----------------------");
        PersonDTOFormView findPersonWithEmail =  personService.findPersonByUserEmail(userDTOForm1.getEmail());
        System.out.println(findPersonWithEmail);
        System.out.println();

        System.out.println("------------------------------TASK SERVICE------------------------------");
        System.out.println("--------------------------------REGISTER--------------------------------");
        Task task1 = new Task("Fix light", null, LocalDate.now().plusDays(10), false);
        Task task2 = new Task("Fix horn", null, LocalDate.now().plusDays(5), false);
        Task task3 = new Task(null, null, LocalDate.now().minusDays(1), false);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        System.out.println();
    }
}

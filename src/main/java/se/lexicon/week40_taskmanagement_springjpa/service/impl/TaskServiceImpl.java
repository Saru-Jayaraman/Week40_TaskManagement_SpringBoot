package se.lexicon.week40_taskmanagement_springjpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.week40_taskmanagement_springjpa.converter.PersonConverter;
import se.lexicon.week40_taskmanagement_springjpa.converter.TaskConverter;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Person;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;
import se.lexicon.week40_taskmanagement_springjpa.exception.DataNotFoundException;
import se.lexicon.week40_taskmanagement_springjpa.repository.PersonRepository;
import se.lexicon.week40_taskmanagement_springjpa.repository.TaskRepository;
import se.lexicon.week40_taskmanagement_springjpa.service.TaskService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    PersonRepository personRepository;
    TaskRepository taskRepository;
    PersonConverter personConverter;
    TaskConverter taskConverter;

    @Autowired
    public TaskServiceImpl(PersonRepository personRepository, TaskRepository taskRepository, PersonConverter personConverter, TaskConverter taskConverter) {
        this.personRepository = personRepository;
        this.taskRepository = taskRepository;
        this.personConverter = personConverter;
        this.taskConverter = taskConverter;
    }

    @Override
    public TaskDTOFormView saveTask(TaskDTOFormSave dto) {
        if(dto == null)
            throw new IllegalArgumentException("Task is null...");
        Task taskEntity = taskConverter.toTaskSave(dto);
        Task savedEntity = taskRepository.save(taskEntity);
        return taskConverter.toTaskDTOView(savedEntity);
    }

    @Override
    public TaskDTOFormView findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Task not found..."));
        return taskConverter.toTaskDTOView(task);
    }

    @Override
    public void update(TaskDTOForm dto) {
        taskRepository.findById(dto.getId())
                .orElseThrow(() -> new DataNotFoundException("Task not found..."));
        Task entity = taskConverter.toTaskForm(dto);
        taskRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Task ID must not be null...");
        if (!taskRepository.existsById(id)) throw new DataNotFoundException("Task not found with id: " + id);
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDTOFormView> findByTaskContainTitle(String title) {
        List<Task> taskEntities = taskRepository.findByTitleContaining(title);
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByPersonId(Long personId) {
        List<Task> taskEntities = taskRepository.findByPerson_Id(personId);
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByDone(boolean done) {
        List<Task> taskEntities = taskRepository.findByDone(done);
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByDeadLineBetween(LocalDate startDate, LocalDate endDate) {
        List<Task> taskEntities = taskRepository.findByDeadLineBetween(startDate, endDate);
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByPersonIsNull() {
        List<Task> taskEntities = taskRepository.findByPersonIsNull();
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByDoneFalse() {
        List<Task> taskEntities = taskRepository.findByDoneFalse();
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> findByDoneFalseAndDeadLineAfter() {
        List<Task> taskEntities = taskRepository.findByDoneFalseAndDeadLineAfter();
        List<TaskDTOFormView> taskDTOViews = new ArrayList<>();
        taskEntities.forEach(eachTask -> taskDTOViews.add(taskConverter.toTaskDTOView(eachTask)));
        return taskDTOViews;
    }

    @Override
    public List<TaskDTOFormView> addTaskToPerson(Long personId, TaskDTOForm... dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new DataNotFoundException("Person not found with id: " + personId));
        List<TaskDTOFormView> views = new ArrayList<>();

        for(TaskDTOForm eachTaskForm : dto) {
            //Adding Task to Person -> Then update
            person.addTask(taskConverter.toTaskForm(eachTaskForm));
            Person savedPerson = personRepository.save(person);

            //Adding Person to Task -> Then update
            eachTaskForm.setPerson(personConverter.toPersonDTOFormEntity(savedPerson));
            update(eachTaskForm);
            views.add(taskConverter.toTaskDTOViewForm(eachTaskForm));
        }
        return views;
    }

    @Override
    public void removeTaskFromPerson(Long personId, TaskDTOForm... dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new DataNotFoundException("Person not found with id: " + personId));

        for(TaskDTOForm eachTaskForm : dto) {
            //Removing Task from Person -> Then update
            person.removeTask(taskConverter.toTaskForm(eachTaskForm));
            personRepository.save(person);

            //Removing Person from Task -> Then update
            eachTaskForm.setPerson(null);
            update(eachTaskForm);
        }
    }
}

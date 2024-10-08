package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.TaskDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Task;

public interface TaskConverter {
    Task toTaskSave(TaskDTOFormSave dto);

    Task toTaskForm(TaskDTOForm dto);

    TaskDTOFormView toTaskDTOView(Task entity);

    TaskDTOFormView toTaskDTOViewForm(TaskDTOForm dto);

    TaskDTOForm toTaskDTOForm(TaskDTOFormView dto);

    TaskDTOForm toTaskDTOFormEntity(Task entity);
}

package se.lexicon.week40_taskmanagement_springjpa.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.*;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Person;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;

@Component
public class PersonConverterImpl implements PersonConverter {

    RoleConverter roleConverter;
    UserConverter userConverter;

    @Autowired
    public PersonConverterImpl(RoleConverter roleConverter, UserConverter userConverter) {
        this.roleConverter = roleConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Person toPersonEntitySave(PersonDTOFormSave dtoFormSave) {
        User user = userConverter.toUserEntityWithoutRoles(dtoFormSave.getUser());
        return Person.builder()
                .name(dtoFormSave.getName())
                .user(user)
                .build();
    }

    @Override
    public PersonDTOFormView toPersonDTOView(Person entity) {
        UserDTOView userDTO = userConverter.toUserDTOView(entity.getUser());
        return PersonDTOFormView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taskList(entity.getTaskList())
                .user(userDTO)
                .build();
    }
}

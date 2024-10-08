package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;

import java.util.Set;

public interface UserConverter {
    User toUserEntity(UserDTOForm dto, Set<Role> roleEntities);

    User toUserEntityWithoutRoles(UserDTOForm dto);

    UserDTOView toUserDTOView(User entity);

    UserDTOView toUserDTOViewForm(UserDTOForm dto);

    UserDTOForm toUserDTOForm(UserDTOView dto);

    UserDTOForm toUserDTOFormEntity(User entity);
}

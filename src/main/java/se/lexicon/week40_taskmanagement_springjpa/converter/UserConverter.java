package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;

import java.util.Set;

public interface UserConverter {
    User toUserEntity(UserDTOForm dto, Set<Role> roleEntities);

    UserDTOView toUserDTOView(User entity);
}

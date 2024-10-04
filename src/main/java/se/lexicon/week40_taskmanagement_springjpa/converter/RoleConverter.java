package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;

public interface RoleConverter {
    Role toRoleEntity(RoleDTOView dto);

    RoleDTOView toRoleDTOView(Role entity);
}

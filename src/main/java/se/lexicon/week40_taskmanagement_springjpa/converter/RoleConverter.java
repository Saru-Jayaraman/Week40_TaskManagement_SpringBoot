package se.lexicon.week40_taskmanagement_springjpa.converter;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;

public interface RoleConverter {
    Role toRoleEntity(RoleDTOFormView dto);

    RoleDTOFormView toRoleDTOView(Role entity);

    Role toRoleEntitySave(RoleDTOFormSave dto);

    RoleDTOForm toRoleDTOForm(RoleDTOFormView viewDTO);
}

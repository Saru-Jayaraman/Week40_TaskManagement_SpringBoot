package se.lexicon.week40_taskmanagement_springjpa.converter;

import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;

@Component
public class RoleConverterImpl implements RoleConverter {
    @Override
    public Role toRoleEntity(RoleDTOView dto) {
        return Role.builder().id(dto.getId()).name(dto.getName()).build();
    }

    @Override
    public RoleDTOView toRoleDTOView(Role entity) {
        return RoleDTOView.builder().id(entity.getId()).name(entity.getName()).build();
    }
}

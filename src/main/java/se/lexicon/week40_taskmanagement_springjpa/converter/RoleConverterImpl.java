package se.lexicon.week40_taskmanagement_springjpa.converter;

import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;

@Component
public class RoleConverterImpl implements RoleConverter {
    @Override
    public Role toRoleEntitySave(RoleDTOFormSave dto) {
        return Role.builder()
                .name(dto.getName())
                .build();
    }

    @Override
    public Role toRoleEntity(RoleDTOForm dto) {
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public RoleDTOFormView toRoleDTOView(Role entity) {
        return RoleDTOFormView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public RoleDTOForm toRoleDTOForm(RoleDTOFormView viewDTO) {
        return RoleDTOForm.builder()
                .id(viewDTO.getId())
                .name(viewDTO.getName())
                .build();
    }
}

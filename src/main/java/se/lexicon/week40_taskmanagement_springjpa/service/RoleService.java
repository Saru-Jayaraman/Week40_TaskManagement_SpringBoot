package se.lexicon.week40_taskmanagement_springjpa.service;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormView;

import java.util.List;

public interface RoleService {
    List<RoleDTOFormView> getAll();

    RoleDTOFormView findByName(String name);

    RoleDTOFormView saveRole(RoleDTOFormSave dtoSaveForm);
}

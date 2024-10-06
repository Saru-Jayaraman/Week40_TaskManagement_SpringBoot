package se.lexicon.week40_taskmanagement_springjpa.service;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormSave;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormView;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTOFormView> getAll();

    Optional<RoleDTOFormView> findByName(String name);

    RoleDTOFormView saveName(RoleDTOFormSave dtoSaveForm);
}

package se.lexicon.week40_taskmanagement_springjpa.service;

import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOView;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTOView> getAll();

    Optional<RoleDTOView> findByName(String name);
}

package se.lexicon.week40_taskmanagement_springjpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.week40_taskmanagement_springjpa.converter.RoleConverter;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.exception.DataNotFoundException;
import se.lexicon.week40_taskmanagement_springjpa.repository.RoleRepository;
import se.lexicon.week40_taskmanagement_springjpa.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public List<RoleDTOView> getAll() {
        List<Role> roleEntities = roleRepository.findAll();
        List<RoleDTOView> roleDTOViews = new ArrayList<>();
        for(Role eachEntity : roleEntities) {
            roleDTOViews.add(roleConverter.toRoleDTOView(eachEntity));
        }
        return roleDTOViews;
    }

    @Override
    public Optional<RoleDTOView> findByName(String name) {
        Optional<Role> roleEntity = roleRepository.findByName(name);
        if(roleEntity.isEmpty())
            throw new DataNotFoundException("Role not found...");
        return Optional.ofNullable(roleConverter.toRoleDTOView(roleEntity.get()));
    }
}

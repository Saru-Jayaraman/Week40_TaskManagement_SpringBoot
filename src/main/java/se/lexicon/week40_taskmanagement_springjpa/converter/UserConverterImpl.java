package se.lexicon.week40_taskmanagement_springjpa.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOFormView;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;
import se.lexicon.week40_taskmanagement_springjpa.util.CustomPasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    RoleConverter roleConverter;
    CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserConverterImpl(RoleConverter roleConverter, CustomPasswordEncoder customPasswordEncoder) {
        this.roleConverter = roleConverter;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public User toUserEntity(UserDTOForm dto, Set<Role> roleEntities) {
        return User.builder()
                .email(dto.getEmail())
                .password(customPasswordEncoder.encode(dto.getPassword()))
                .roles(roleEntities)
                .build();
    }

    @Override
    public User toUserEntityWithoutRoles(UserDTOForm dto) {
        Set<Role> roles = dto.getRoles()
                .stream()
                .map(role -> roleConverter.toRoleEntity(role))
                .collect(Collectors.toSet());
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public UserDTOView toUserDTOView(User entity) {
        Set<RoleDTOFormView> roleDTOFormViews = entity.getRoles()
                .stream()
                .map(role -> roleConverter.toRoleDTOView(role))
                .collect(Collectors.toSet());
        return UserDTOView.builder()
                .email(entity.getEmail())
                .roles(roleDTOFormViews)
                .build();
    }
}

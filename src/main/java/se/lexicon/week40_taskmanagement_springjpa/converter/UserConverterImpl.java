package se.lexicon.week40_taskmanagement_springjpa.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.RoleDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOForm;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.UserDTOView;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.User;
import se.lexicon.week40_taskmanagement_springjpa.util.CustomPasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserConverterImpl(CustomPasswordEncoder customPasswordEncoder) {
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
    public UserDTOView toUserDTOView(User entity) {
        Set<RoleDTOView> roleDTOViews = entity.getRoles()
                .stream()
                .map(role -> RoleDTOView.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .build())
                .collect(Collectors.toSet());
        return UserDTOView.builder()
                .email(entity.getEmail())
                .roles(roleDTOViews)
                .build();
    }
}

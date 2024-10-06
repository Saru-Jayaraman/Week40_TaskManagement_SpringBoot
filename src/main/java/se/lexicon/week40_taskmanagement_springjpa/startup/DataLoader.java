package se.lexicon.week40_taskmanagement_springjpa.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.week40_taskmanagement_springjpa.converter.RoleConverter;
import se.lexicon.week40_taskmanagement_springjpa.domain.dto.*;
import se.lexicon.week40_taskmanagement_springjpa.service.RoleService;
import se.lexicon.week40_taskmanagement_springjpa.service.UserService;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    RoleConverter roleConverter;

    @Autowired
    public DataLoader(RoleService roleService, UserService userService, RoleConverter roleConverter) {
        this.roleService = roleService;
        this.userService = userService;
        this.roleConverter = roleConverter;
    }

    public DataLoader() {
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        RoleDTOFormView savedUser1 = roleService.saveName(new RoleDTOFormSave("ADMIN"));
        roleService.saveName(new RoleDTOFormSave("USER"));
        roleService.saveName(new RoleDTOFormSave("GUEST"));

        System.out.println("------------------------------ROLE SERVICE------------------------------");
        System.out.println("--------------------------------FIND ALL--------------------------------");
        List<RoleDTOFormView> allRolesFormView = roleService.getAll();
        allRolesFormView.forEach(System.out::println);

        System.out.println("----------------------------FIND BY USERNAME----------------------------");
        Optional<RoleDTOFormView> findByRoleName = roleService.findByName(savedUser1.getName());
        findByRoleName.ifPresent(System.out::println);

        List<RoleDTOForm> allRolesForm = new ArrayList<>();
        for(RoleDTOFormView eachDTO : allRolesFormView) {
            allRolesForm.add(roleConverter.toRoleDTOForm(eachDTO));
        }

        UserDTOForm userDTOForm1 = new UserDTOForm("test1@gmail.com", "test1", Set.of(allRolesForm.get(1)));
        UserDTOForm userDTOForm2 = new UserDTOForm("test2@gmail.com", "test2", Set.of(allRolesForm.get(2)));

        System.out.println("------------------------------USER SERVICE------------------------------");
        System.out.println("--------------------------------REGISTER--------------------------------");
        UserDTOView userDTOView1 = userService.register(userDTOForm1);
        UserDTOView userDTOView2 = userService.register(userDTOForm2);
        System.out.println(userDTOView1);
        System.out.println(userDTOView2);

        System.out.println("-----------------------------UPDATE PASSWORD----------------------------");
        userService.updatePassword(userDTOForm1.getEmail(), "test11");

        System.out.println("-----------------------------DISABLE EXPIRED----------------------------");
        userService.disableByEmail(userDTOForm1.getEmail());

        System.out.println("-------------------------------GET BY EMAIL-----------------------------");
        UserDTOView getRoleByEmail = userService.getByEmail(userDTOForm1.getEmail());
        System.out.println(getRoleByEmail);

        System.out.println("-----------------------------ENABLE EXPIRED-----------------------------");
        userService.enableByEmail(userDTOForm1.getEmail());
    }
}

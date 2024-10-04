package se.lexicon.week40_taskmanagement_springjpa.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.week40_taskmanagement_springjpa.domain.entity.Role;
import se.lexicon.week40_taskmanagement_springjpa.repository.RoleRepository;

@Component
public class RoleDataLoader implements CommandLineRunner {

    RoleRepository roleRepository;

    @Autowired
    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));
    }
}

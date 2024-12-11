

package com.example.anlasmalikurumlar.config;

import com.example.anlasmalikurumlar.model.Role;
import com.example.anlasmalikurumlar.model.User;
import com.example.anlasmalikurumlar.repository.RoleRepository;
import com.example.anlasmalikurumlar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        Role userRole = new Role();
        Role adminRole = new Role();

        userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }


        adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

        }

        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);


        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        user.setRoles(Set.of(userRole));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setRoles(Set.of(adminRole));


        if (userRepository.findByUsername(user.getUsername()) == null)
            userRepository.save(user);
        if (userRepository.findByUsername(admin.getUsername()) == null)
            userRepository.save(admin);
    }

}


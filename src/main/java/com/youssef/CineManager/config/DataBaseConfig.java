package com.youssef.CineManager.config;


import com.youssef.CineManager.security.model.ERole;
import com.youssef.CineManager.security.model.Role;
import com.youssef.CineManager.security.model.User;
import com.youssef.CineManager.security.repository.RoleRepository;
import com.youssef.CineManager.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataBaseConfig {

    @Autowired
    private PasswordEncoder encoder;



    @Bean
    public CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            // Initialize roles if they don't exist
            Role userRole = null;
            Role adminRole = null;

            if (roleRepository.count() == 0) {
                userRole = new Role();
                userRole.setName(ERole.ROLE_USER);
                roleRepository.save(userRole);

                adminRole = new Role();
                adminRole.setName(ERole.ROLE_ADMIN);
                roleRepository.save(adminRole);

                System.out.println("Roles initialized successfully");
            } else {
                userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }

            // Create a default admin user if not exists
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User(
                        "admin",
                        "admin@example.com",
                        encoder.encode("password123")
                );

                Set<Role> roles = new HashSet<>();
//                roles.add(userRole);
                roles.add(adminRole);
                admin.setRoles(roles);

                userRepository.save(admin);
                System.out.println("Default admin user created successfully");
            }
        };
    }
}
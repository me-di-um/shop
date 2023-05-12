package com.abaimov.jwtshop;

import com.abaimov.jwtshop.user.Role;
import com.abaimov.jwtshop.user.RoleRepository;
import com.abaimov.jwtshop.user.User;
import com.abaimov.jwtshop.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JwtShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtShopApplication.class, args);
    }


    @Bean
    CommandLineRunner run(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder){
        return args->{
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("MANAGER"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User("admin@gmail.com", passwordEncoder.encode("1"), roles);
            userRepository.save(admin);

        };
    }
}

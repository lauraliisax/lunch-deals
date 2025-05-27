
package com.app.lunchDeals.configuration;

import com.app.lunchDeals.entity.User;
import com.app.lunchDeals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class TestUserDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String testUsername = "testuser";
        if (userRepository.findByUsername(testUsername).isEmpty()) {
            User user = new User();
            user.setUsername(testUsername);
            user.setPassword(passwordEncoder.encode("testpassword"));
            user.setRoles(new HashSet<>(Collections.singletonList("ADMIN")));
            userRepository.save(user);
            System.out.println("Test user created: username='testuser', password='testpassword', role='ADMIN'");
        }
    }
}

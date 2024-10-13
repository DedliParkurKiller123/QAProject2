package org.sec.jwtsecurityproject.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.sec.jwtsecurityproject.adminPanel.model.AdminPanel;
import org.sec.jwtsecurityproject.adminPanel.repository.AdminRepo;
import org.sec.jwtsecurityproject.user.model.User;
import org.sec.jwtsecurityproject.user.repository.UserRepository;
import org.sec.jwtsecurityproject.user.role.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByPhoneNumber(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(AdminRepo adminRepo, UserRepository userRepository) {
        return args -> {
            adminRepo.saveAll(List.of(
                    new AdminPanel(
                            "Laptop Asus",
                            39122.99,
                            LocalDate.of(2021, Month.AUGUST, 10),
                            "Good laptop, great laptop!"
                    ),
                    new AdminPanel(
                            "Samsung smartphone",
                            12399.99,
                            LocalDate.of(2022, Month.AUGUST, 10),
                            "Good smartphone, great smartphone!"
                    )
            ));
            userRepository.save(
                    new User(
                            "Ryan Gosling",
                            LocalDate.of(1980, Month.NOVEMBER, 12),
                            "0000000000",
                            "RyanGoslingDriver666@gmail.com",
                            passwordEncoder().encode("Ana_Celia_de_Armas_Caso"),
                            Role.ADMIN
                    )
            );
        };
    }

}

package com.kosowski.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // {noop} means that the password is stored in plain text, without any encoding,
        // There are other options for password encoding, such as argon2
        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
        manager.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());
        return manager;
    }
}

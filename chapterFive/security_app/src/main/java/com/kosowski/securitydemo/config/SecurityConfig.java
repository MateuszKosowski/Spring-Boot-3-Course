package com.kosowski.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // {noop} means that the password is stored in plain text, without any encoding,
        // There are other options for password encoding, such as argon2
        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
        manager.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());
        manager.createUser(User.withUsername("viewer").password("{noop}viewer").roles("VIEWER").build());
        return manager;
    }

    @Bean
    SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.GET,"/api/v1/employees/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/v1/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.httpBasic(Customizer.withDefaults()).build();
    }




}

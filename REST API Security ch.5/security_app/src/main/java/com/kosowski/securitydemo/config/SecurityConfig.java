package com.kosowski.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        // {noop} means that the password is stored in plain text, without any encoding,
//        // There are other options for password encoding, such as argon2
//        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
//        manager.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());
//        manager.createUser(User.withUsername("viewer").password("{noop}viewer").roles("VIEWER").build());
//        return manager;
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active FROM members WHERE user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id, role FROM roles WHERE user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.GET,"/api/v1/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/v1/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/employees/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.httpBasic(Customizer.withDefaults()).build();
    }




}

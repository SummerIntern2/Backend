package com.example.backend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity(debug = true)
public class ApplicationSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests((requests) -> requests
        //         .requestMatchers("/signUp").permitAll()
        //         .anyRequest().authenticated())
        //     .formLogin((form) -> form.loginPage("/login").permitAll())
        //     .logout((logout) -> logout.permitAll());

        http
            .authorizeHttpRequests((requests) -> 
                    requests.requestMatchers("/**").permitAll().anyRequest().authenticated())
            .csrf(csrf -> csrf.disable());
        // http.authorizeHttpRequests((requests) -> requests.requestMatchers("/**").permitAll());antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();

        
        return http.build();
    }
}

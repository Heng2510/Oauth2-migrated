package com.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable csrf (Cross Site Request Forgery)
                // Instead of AbstractHttpConfigurer::disable
                // We can use csrf(t -> t.disabled()) for readable
                .csrf(AbstractHttpConfigurer::disable)
                // This is code after migrated spring security
                // Instead of
                //    http
                //        .csrf()
                //        .disable()
                //        .authorizeHttpRequests()
                //        .anyRequest()
                //        .authenticated()
                //        .and()
                //        .oauth2Login();
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                ).oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/hello", true)); // Redirect to /home

        return httpSecurity.build();
    }

}

package com.demo.controllerWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/**").permitAll()
/*                                        .requestMatchers("/api/auth/login").permitAll()
                                  .requestMatchers("/index").authenticated()
                                  .requestMatchers("/api/clients/**").hasAuthority("ROLE_ADMIN")
                                  .requestMatchers("/api/categories/**").permitAll()
                                  .requestMatchers("/api/manufacturers/**").permitAll()
                                  .requestMatchers("/api/orders/**").permitAll()
                                  .requestMatchers("/api/products/**").permitAll()
                                  .requestMatchers("/api/shippings/**").permitAll()*/
                )/*.addFilterBefore(jwtAuthenticationFilter(
                                jwtTokenProvider,
                                myClientService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll
                ).build();
    }

}

package com.diegogin.motorcyclesapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

            http.csrf(csrf -> csrf.spa())

            .authorizeHttpRequests(

            auth -> auth.requestMatchers(

            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/login").permitAll()

                .requestMatchers(HttpMethod.GET, "/api/motorcycles/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/auth/status").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/motorcycles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/motorcycles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/motorcycles/**").hasRole("ADMIN")
                .anyRequest().authenticated()

            )
            .exceptionHandling(exception -> exception

                .defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        PathPatternRequestMatcher.withDefaults().matcher("/api/**")
                )
            )
            .formLogin(form -> form.defaultSuccessUrl("/swagger-ui/index.html", true)
            .permitAll()
            ).logout(logout -> logout
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            @Value("${app.admin.username}") String username,
            @Value("${app.admin.password}") String password,
            PasswordEncoder passwordEncoder
    ) {

        UserDetails admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);

    }



}

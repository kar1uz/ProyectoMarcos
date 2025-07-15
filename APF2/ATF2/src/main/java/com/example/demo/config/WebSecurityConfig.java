package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/iniciarSesion**", "/h2-console/**", "/registrate**", "/contactanos**",
                    "/assets/**", "/css/**", "/js/**", "/img/**", "/error", "/api/**") // <- Añadir "/api/**"
                .permitAll()
                .requestMatchers("/indexAdmin").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/iniciarSesion")
                        .loginProcessingUrl("/iniciarSesion")
                        // .usernameParameter("email") // <-- COMENTA O ELIMINA ESTA LÍNEA
                        .defaultSuccessUrl("/postLoginRedirect", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/iniciarSesion?logout")
                        .permitAll())

                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) 
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
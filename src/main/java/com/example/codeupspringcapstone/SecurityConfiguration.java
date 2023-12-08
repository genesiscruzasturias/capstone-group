package com.example.codeupspringcapstone;

import com.example.codeupspringcapstone.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/reviews/create", "/reviews/*/edit","/profile", "/view-brewery").authenticated()
                        .requestMatchers("/", "/reviews", "/reviews/*", "/sign-up", "/sign-in", "/view-breweries").permitAll()
                        /* Pages that require authentication
                         * only authenticated users can create and edit ads */
//<<<<<<< HEAD
                        .requestMatchers("/reviews/create", "/reviews/*/edit","/profile", "/edit-profile").authenticated()
//=======
//>>>>>>> 4ddebbdcfb8e18bce5ca375744ad1e0efe82063d
                        /* Pages that do not require authentication
                         * anyone can visit the home page, register, login, and view ads */
//                        .requestMatchers("/", "/reviews", "/reviews/*", "/sign-up", "/sign-in", "/view-breweries", "/view-brewery").permitAll()
                        // allow loading of static resources
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                )
                /* Login configuration */
                .formLogin((login) -> login.loginPage("/sign-in").defaultSuccessUrl("/profile"))
                /* Logout configuration */
                .logout((logout) -> logout.logoutSuccessUrl("/sign-in"))
                .httpBasic(withDefaults());
        return http.build();
    }

}

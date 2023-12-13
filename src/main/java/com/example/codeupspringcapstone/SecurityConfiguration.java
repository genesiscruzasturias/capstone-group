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

<<<<<<< HEAD
                        .requestMatchers("/reviews/create", "/reviews/*/edit","/profile", "/create", "/profile/edit/{id}", "/profile/edit", "/delete-profile","/delete-profile/{id}").authenticated()
=======
                        .requestMatchers("/reviews/create", "/review/edit","/profile", "/create", "/profile/edit/{id}", "/profile/edit", "/profile/edit-review", "/view-review/{id}").authenticated()
>>>>>>> b22175e (on main accidentally)
                        .requestMatchers("/", "/reviews", "/reviews/*", "/sign-up", "/sign-in", "/view-breweries", "/view-brewery").permitAll()

                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll())
                .formLogin((login) -> login.loginPage("/sign-in").defaultSuccessUrl("/profile", true))
                .logout((logout) -> logout.logoutSuccessUrl("/sign-in"))

                .httpBasic(withDefaults());

        return http.build();
    }

}
package com.mmddvg.taskmanager.config;


import com.mmddvg.taskmanager.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] WHITE_LIST ={"/auth/**"};

    private final JwtAuthenticationFilter jwtAuthFiler;
    private final AuthenticationProvider authProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFiler, AuthenticationProvider authProvider) {
        this.jwtAuthFiler = jwtAuthFiler;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST)
                        .permitAll()
                       .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFiler, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

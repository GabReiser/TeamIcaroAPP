package com.teamicaro.TeamIcaroAPP.Config;

import com.teamicaro.TeamIcaroAPP.Jwt.JwtAuthenticationFilter;
import com.teamicaro.TeamIcaroAPP.Repository.UserRepository;
import com.teamicaro.TeamIcaroAPP.Security.CustomOidcUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthFilter ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomOidcUserService customOidcUserService, // Serviço customizado para carregar informações do usuário OAuth2
            OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler // Manipulador de sucesso OAuth2
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura como Stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/oauth2/**").permitAll() // Permite acesso público a endpoints de autenticação
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Apenas ADMIN pode acessar /admin/**
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER ou ADMIN podem acessar /user/**
                        .anyRequest().authenticated() // Outros endpoints exigem autenticação
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(customOidcUserService)) // Configura UserInfo para OAuth2
                        .successHandler(oAuth2AuthenticationSuccessHandler) // Handler de sucesso para login OAuth2
                        .loginPage("/auth/login/google") // Endpoint customizado para login com Google
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint()) // Configura EntryPoint para erros de autenticação
                        .accessDeniedHandler(accessDeniedHandler()) // Configura handler para erros de autorização
                )
                .formLogin(form -> form.disable()); // Desativa o login via formulário padrão

        // Adiciona o filtro JWT antes do UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Certifique-se de que `userDetailsService` está definido
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Acesso negado. Faça login novamente.\"}");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Você não tem permissão para acessar este recurso.\"}");
        };
    }
}
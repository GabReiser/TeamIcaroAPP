package com.teamicaro.TeamIcaroAPP.Config;

import com.teamicaro.TeamIcaroAPP.Model.Role;
import com.teamicaro.TeamIcaroAPP.Service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;

    public OAuth2AuthenticationSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // 'authentication' deve ser um OAuth2AuthenticationToken
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();

        // gerar token
        String token = jwtService.generateToken(email, Set.of(Role.USER));

        // redirecionar para front-end com token como query param
        response.sendRedirect("http://localhost:4200/oauth-success?token=" + token);
    }

}
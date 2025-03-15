package com.teamicaro.TeamIcaroAPP.Controller;

import com.teamicaro.TeamIcaroAPP.DTO.LoginDTO;
import com.teamicaro.TeamIcaroAPP.DTO.RegisterDTO;
import com.teamicaro.TeamIcaroAPP.Model.Role;
import com.teamicaro.TeamIcaroAPP.Model.User;
import com.teamicaro.TeamIcaroAPP.Service.JwtService;
import com.teamicaro.TeamIcaroAPP.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registro de usuário.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDto) {
        // Verifica se o email já existe
        if (userService.buscarPorEmail(registerDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já está em uso!");
        }

        // Criar novo usuário e persistir no banco
        User newUser = User.builder()
                .nome(registerDto.getName())
                .email(registerDto.getEmail())
                .senha(passwordEncoder.encode(registerDto.getPassword()))
                .roles(Set.of(Role.USER)) // Role padrão USER
                .xp(0)
                .nivel(1)
                .build();

        userService.salvarUsuario(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    /**
     * Login normal com email e senha.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        // Busca o usuário no banco de dados
        Optional<User> userOptional = userService.buscarPorEmail(loginDTO.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        User user = userOptional.get();

        // Valida senha
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        // Autenticação do usuário
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        // Gera token JWT
        String jwt = jwtService.generateToken(user.getEmail(), user.getRoles());

        // Retorna o token em JSON
        return ResponseEntity.ok().body("{\"token\":\"" + jwt + "\"}");
    }

    /**
     * Login com Google OAuth2.
     */
    @GetMapping("/login/google")
    public void redirectToGoogleLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }
}

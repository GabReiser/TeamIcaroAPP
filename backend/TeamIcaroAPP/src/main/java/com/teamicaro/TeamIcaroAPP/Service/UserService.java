package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Role;
import com.teamicaro.TeamIcaroAPP.Model.User;
import com.teamicaro.TeamIcaroAPP.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User salvarUsuario(User user) {
        return userRepository.save(user);
    }

    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Cria um novo usuário no MongoDB com dados básicos.
     */
    public User createUserWithGoogle(String name, String email, String password) {
        User user = new User();
        user.setNome(name);
        user.setEmail(email);
        user.setSenha(password); // senha crua

        // se quiser atribuir role padrão:
        user.setRoles(Set.of(Role.USER));

        return userRepository.save(user);
    }

    /**
     * Cria um usuário caso ele não exista; se já existir, retorna o existente.
     */
    public User createUserIfNotExists(String name, String email, String phoneNumber, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // Se o usuário não existir, criar um novo
        System.out.println("Criando usuário: " + email);
        return createUserWithGoogle(name, email, password);
    }
}
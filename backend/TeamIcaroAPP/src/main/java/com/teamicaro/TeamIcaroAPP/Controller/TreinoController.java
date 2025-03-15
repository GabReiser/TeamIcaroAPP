package com.teamicaro.TeamIcaroAPP.Controller;

import com.teamicaro.TeamIcaroAPP.DTO.TreinoDTO;
import com.teamicaro.TeamIcaroAPP.Model.Exercicio;
import com.teamicaro.TeamIcaroAPP.Model.Treino;
import com.teamicaro.TeamIcaroAPP.Model.User;
import com.teamicaro.TeamIcaroAPP.Service.ExercicioService;
import com.teamicaro.TeamIcaroAPP.Service.TreinoService;
import com.teamicaro.TeamIcaroAPP.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService treinoService;
    private final UserService userService;
    private final ExercicioService exercicioService;

    public TreinoController(TreinoService treinoService, UserService userService, ExercicioService exercicioService) {
        this.treinoService = treinoService;
        this.userService = userService;
        this.exercicioService = exercicioService;
    }

    @PostMapping
    public ResponseEntity<Treino> criarTreino(@RequestBody TreinoDTO treinoDTO) {
        // Busca o usuário pelo ID
        User user = userService.buscarPorId(treinoDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca os exercícios pelos IDs
        List<Exercicio> exercicios = exercicioService.buscarPorIds(treinoDTO.getExercicioIds());

        // Criamos o treino
        Treino treino = Treino.builder()
                .nome(treinoDTO.getNome())
                .descricao(treinoDTO.getDescricao())
                .user(user) // Associa ao usuário
                .exercicios(new HashSet<>(exercicios)) // Associa os exercícios
                .build();

        Treino novoTreino = treinoService.salvarTreino(treino);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTreino);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Treino>> listarTreinos(@PathVariable Long userId) {
        return ResponseEntity.ok(treinoService.buscarTreinosPorUsuario(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTreino(@PathVariable Long id) {
        treinoService.excluirTreino(id);
        return ResponseEntity.noContent().build();
    }
}
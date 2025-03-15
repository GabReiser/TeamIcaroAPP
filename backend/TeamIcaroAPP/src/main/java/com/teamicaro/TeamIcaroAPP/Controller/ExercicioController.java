package com.teamicaro.TeamIcaroAPP.Controller;

import com.teamicaro.TeamIcaroAPP.DTO.ExercicioDTO;
import com.teamicaro.TeamIcaroAPP.Model.Exercicio;
import com.teamicaro.TeamIcaroAPP.Service.ExercicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @PostMapping
    public ResponseEntity<Exercicio> criarExercicio(@RequestBody ExercicioDTO exercicioDTO) {
        Exercicio exercicio = Exercicio.builder()
                .nome(exercicioDTO.getNome())
                .instrucoes(exercicioDTO.getInstrucoes())
                .series(exercicioDTO.getSeries())
                .repeticoes(exercicioDTO.getRepeticoes())
                .carga(exercicioDTO.getCarga())
                .videoUrl(exercicioDTO.getVideoUrl())
                .build();

        Exercicio novoExercicio = exercicioService.criarExercicio(exercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoExercicio);
    }

    @GetMapping("get/{exercicioId}")
    public ResponseEntity<List<Exercicio>> listarExercicios(@PathVariable Long treinoId) {
        return ResponseEntity.ok(exercicioService.listarExerciciosPorTreino(treinoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirExercicio(@PathVariable Long id) {
        exercicioService.excluirExercicio(id);
        return ResponseEntity.noContent().build();
    }
}

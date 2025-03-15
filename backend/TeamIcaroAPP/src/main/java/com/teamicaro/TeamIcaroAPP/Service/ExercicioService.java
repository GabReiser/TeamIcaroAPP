package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Exercicio;
import com.teamicaro.TeamIcaroAPP.Repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public Exercicio criarExercicio(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public List<Exercicio> listarExerciciosPorTreino(Long treinoId) {
        return exercicioRepository.findByTreinoId(treinoId);
    }

    public void excluirExercicio(Long id) {
        exercicioRepository.deleteById(id);
    }

    public List<Exercicio> buscarPorIds(List<Long> ids) {
        return exercicioRepository.findAllById(ids);
    }
}

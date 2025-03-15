package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Dieta;
import com.teamicaro.TeamIcaroAPP.Repository.DietaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietaService {

    private final DietaRepository dietaRepository;

    public DietaService(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }

    public List<Dieta> buscarDietasPorUsuario(Long userId) {
        return dietaRepository.findByUserId(userId);
    }

    public Dieta salvarDieta(Dieta dieta) {
        return dietaRepository.save(dieta);
    }

    public void excluirDieta(Long dietaId) {
        dietaRepository.deleteById(dietaId);
    }
}
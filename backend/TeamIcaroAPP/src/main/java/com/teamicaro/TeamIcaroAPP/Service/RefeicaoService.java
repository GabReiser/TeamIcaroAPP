package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Refeicao;
import com.teamicaro.TeamIcaroAPP.Repository.RefeicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

    public Refeicao criarRefeicao(Refeicao refeicao) {
        return refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listarRefeicoesPorDieta(Long dietaId) {
        return refeicaoRepository.findByDietaId(dietaId);
    }

    public void excluirRefeicao(Long id) {
        refeicaoRepository.deleteById(id);
    }
}

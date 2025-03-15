package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Treino;
import com.teamicaro.TeamIcaroAPP.Repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public List<Treino> buscarTreinosPorUsuario(Long userId) {
        return treinoRepository.findByUserId(userId);
    }

    public Treino salvarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    public void excluirTreino(Long treinoId) {
        treinoRepository.deleteById(treinoId);
    }
}
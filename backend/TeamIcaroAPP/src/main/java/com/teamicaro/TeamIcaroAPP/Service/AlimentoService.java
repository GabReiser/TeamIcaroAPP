package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Alimento;
import com.teamicaro.TeamIcaroAPP.Repository.AlimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public Alimento adicionarAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public List<Alimento> listarAlimentosPorRefeicao(Long refeicaoId) {
        return alimentoRepository.findByRefeicaoId(refeicaoId);
    }

    public void excluirAlimento(Long id) {
        alimentoRepository.deleteById(id);
    }
}

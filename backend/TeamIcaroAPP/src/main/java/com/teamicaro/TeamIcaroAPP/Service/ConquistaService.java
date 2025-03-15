package com.teamicaro.TeamIcaroAPP.Service;

import com.teamicaro.TeamIcaroAPP.Model.Conquista;
import com.teamicaro.TeamIcaroAPP.Repository.ConquistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConquistaService {

    private final ConquistaRepository conquistaRepository;

    public ConquistaService(ConquistaRepository conquistaRepository) {
        this.conquistaRepository = conquistaRepository;
    }

    public List<Conquista> buscarConquistasPorUsuario(Long userId) {
        return conquistaRepository.findByUserId(userId);
    }

    public Conquista salvarConquista(Conquista conquista) {
        return conquistaRepository.save(conquista);
    }
}
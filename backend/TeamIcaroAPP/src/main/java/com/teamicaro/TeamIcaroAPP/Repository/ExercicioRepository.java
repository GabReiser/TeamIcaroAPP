package com.teamicaro.TeamIcaroAPP.Repository;

import com.teamicaro.TeamIcaroAPP.Model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> findByTreinoId(Long treinoId);
}
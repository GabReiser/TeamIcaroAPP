package com.teamicaro.TeamIcaroAPP.Repository;

import com.teamicaro.TeamIcaroAPP.Model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    List<Treino> findByUserId(Long userId);
}
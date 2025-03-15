package com.teamicaro.TeamIcaroAPP.Repository;

import com.teamicaro.TeamIcaroAPP.Model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    List<Alimento> findByRefeicaoId(Long refeicaoId);
}

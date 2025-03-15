package com.teamicaro.TeamIcaroAPP.Repository;

import com.teamicaro.TeamIcaroAPP.Model.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByDietaId(Long dietaId);
}

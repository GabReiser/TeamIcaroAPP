package com.teamicaro.TeamIcaroAPP.Repository;

import com.teamicaro.TeamIcaroAPP.Model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    List<Conquista> findByUserId(Long userId);
}

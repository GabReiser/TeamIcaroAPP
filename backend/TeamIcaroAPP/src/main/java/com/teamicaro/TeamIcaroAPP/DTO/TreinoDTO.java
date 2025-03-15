package com.teamicaro.TeamIcaroAPP.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TreinoDTO {
    private String nome;
    private String descricao;
    private Long userId; // Apenas o ID do usuário
    private List<Long> exercicioIds; // Lista de IDs dos exercícios que farão parte do treino
}

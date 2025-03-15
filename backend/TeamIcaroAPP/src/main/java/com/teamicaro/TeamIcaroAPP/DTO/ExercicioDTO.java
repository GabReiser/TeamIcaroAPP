package com.teamicaro.TeamIcaroAPP.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioDTO {
    private String nome;
    private String instrucoes;
    private int series;
    private int repeticoes;
    private Double carga;
    private String videoUrl;
}

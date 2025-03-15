package com.teamicaro.TeamIcaroAPP.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "exercicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Exemplo: "Supino reto", "Agachamento livre"

    @Column(nullable = false, columnDefinition = "TEXT")
    private String instrucoes; // Como realizar o exercício

    @Column(nullable = false)
    private int series; // Número de séries

    @Column(nullable = false)
    private int repeticoes; // Número de repetições por série

    @Column
    private Double carga; // Carga usada (opcional, pode ser nulo)

    @Column
    private String videoUrl; // URL de um vídeo demonstrativo

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = true) // Permite criar exercício sem treino
    private Treino treino;
}

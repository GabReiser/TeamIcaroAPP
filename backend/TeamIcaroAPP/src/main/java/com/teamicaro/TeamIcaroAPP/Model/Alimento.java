package com.teamicaro.TeamIcaroAPP.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Exemplo: "Arroz", "Peito de Frango", "Banana"

    @Column(nullable = false)
    private String quantidade; // Exemplo: "100g", "1 porção", "2 fatias"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAlimento tipoAlimento; // Classificação do alimento

    @ManyToOne
    @JoinColumn(name = "refeicao_id", nullable = false)
    private Refeicao refeicao;
}

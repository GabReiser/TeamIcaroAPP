package com.teamicaro.TeamIcaroAPP.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "refeicoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Exemplo: "Café da manhã", "Almoço"

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alimento> alimentos; // Lista de alimentos na refeição

    @ManyToOne
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;
}

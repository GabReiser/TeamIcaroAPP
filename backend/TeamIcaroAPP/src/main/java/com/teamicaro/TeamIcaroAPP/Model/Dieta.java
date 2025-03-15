package com.teamicaro.TeamIcaroAPP.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "dietas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Refeicao> refeicoes;
}

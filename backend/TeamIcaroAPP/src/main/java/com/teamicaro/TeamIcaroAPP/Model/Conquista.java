package com.teamicaro.TeamIcaroAPP.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conquistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String icone; // Ex: "medalha-ouro"

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
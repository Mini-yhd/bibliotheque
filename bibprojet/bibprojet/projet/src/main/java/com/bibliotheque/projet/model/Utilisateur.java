package com.bibliotheque.projet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String motDePasse;
    private String email;
    private boolean actif;

    public Utilisateur(Long id) {
        this.id = id;
    }
}
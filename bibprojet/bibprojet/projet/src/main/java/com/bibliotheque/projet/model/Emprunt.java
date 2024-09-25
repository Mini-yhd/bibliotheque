package com.bibliotheque.projet.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Emprunt {
    @Id
    private Long id;
    private Date dateEmprunt;
    private Date dateRetour;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur adherent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livre_id")
    private Livre livre;
}

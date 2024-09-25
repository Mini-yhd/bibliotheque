package com.bibliotheque.projet.repository;

import com.bibliotheque.projet.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
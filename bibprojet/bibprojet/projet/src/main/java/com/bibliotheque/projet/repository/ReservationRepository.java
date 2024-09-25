package com.bibliotheque.projet.repository;

import com.bibliotheque.projet.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
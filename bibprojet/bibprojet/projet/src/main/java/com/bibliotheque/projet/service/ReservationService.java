package com.bibliotheque.projet.service;

import com.bibliotheque.projet.model.Livre;
import com.bibliotheque.projet.model.Reservation;
import com.bibliotheque.projet.model.Utilisateur;
import com.bibliotheque.projet.repository.LivreRepository;
import com.bibliotheque.projet.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LivreRepository livreRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = getReservationById(id);
        existingReservation.setId(reservation.getId());
        existingReservation.setDateDebut(reservation.getDateDebut());
        existingReservation.setDateFin(reservation.getDateFin());
        existingReservation.setClient(reservation.getClient());
        existingReservation.setLivre(reservation.getLivre());
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation reserveBook(Long livreId, Long clientId, Date dateDebut, Date dateFin) {
        Livre livre = livreRepository.findById(livreId).orElseThrow();

        // Check if the book is currently borrowed
        if (livre.isEmprunte()) {
            Reservation reservation = new Reservation();
            reservation.setLivre(livre);
            reservation.setClient(new Utilisateur(clientId));
            reservation.setDateDebut(dateDebut);
            reservation.setDateFin(dateFin);
            return reservationRepository.save(reservation);
        } else {
            throw new IllegalStateException("The book is not currently borrowed.");
        }
    }
}

package com.bibliotheque.projet.controller;

import com.bibliotheque.projet.model.Reservation;
import com.bibliotheque.projet.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Liens de mappage pour ReservationController
     *
     * | Méthode HTTP | URI                       | Description                                     |
     * |--------------|---------------------------|-------------------------------------------------|
     * | GET          | /reservations              | Récupérer toutes les réservations               |
     * | GET          | /reservations/{id}         | Récupérer une réservation par ID                |
     * | POST         | /reservations              | Créer une nouvelle réservation                  |
     * | PUT          | /reservations/{id}         | Mettre à jour une réservation existante         |
     * | DELETE       | /reservations/{id}         | Supprimer une réservation par ID                |
     * | POST         | /reservations/reserve      | Réserver un livre avec livreId, clientId, date  |
     */

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserveBook(@RequestParam Long livreId, @RequestParam Long clientId,
                                                   @RequestParam Date dateDebut, @RequestParam Date dateFin) {
        Reservation reservation = reservationService.reserveBook(livreId, clientId, dateDebut, dateFin);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}

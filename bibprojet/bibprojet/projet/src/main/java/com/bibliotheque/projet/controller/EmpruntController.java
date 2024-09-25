package com.bibliotheque.projet.controller;

import com.bibliotheque.projet.model.Emprunt;
import com.bibliotheque.projet.model.Livre;
import com.bibliotheque.projet.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    /**
     * Liens de mappage pour EmpruntController
     *
     * | Méthode HTTP | URI                      | Description                                   |
     * |--------------|--------------------------|-----------------------------------------------|
     * | GET          | /emprunts                 | Récupérer tous les emprunts                   |
     * | GET          | /emprunts/{id}            | Récupérer un emprunt par ID                   |
     * | POST         | /emprunts                 | Créer un nouvel emprunt                       |
     * | PUT          | /emprunts/{id}            | Mettre à jour un emprunt existant             |
     * | DELETE       | /emprunts/{id}            | Supprimer un emprunt par ID                   |
     * | GET          | /emprunts/available-books | Récupérer la liste des livres disponibles     |
     * | POST         | /emprunts/borrow          | Emprunter un livre (livreId et utilisateurId) |
     * | POST         | /emprunts/return/{id}     | Retourner un livre emprunté par ID d'emprunt  |
     */

    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        List<Emprunt> emprunts = empruntService.getAllEmprunts();
        return new ResponseEntity<>(emprunts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt != null) {
            return new ResponseEntity<>(emprunt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody Emprunt emprunt) {
        Emprunt createdEmprunt = empruntService.createEmprunt(emprunt);
        return new ResponseEntity<>(createdEmprunt, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @RequestBody Emprunt emprunt) {
        Emprunt updatedEmprunt = empruntService.updateEmprunt(id, emprunt);
        if (updatedEmprunt != null) {
            return new ResponseEntity<>(updatedEmprunt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        Emprunt emprunt = empruntService.getEmpruntById(id);
        if (emprunt != null) {
            empruntService.deleteEmprunt(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/available-books")
    public ResponseEntity<List<Livre>> getAvailableBooks() {
        List<Livre> livres = empruntService.getAvailableBooks();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @PostMapping("/borrow")
    public ResponseEntity<Emprunt> borrowBook(@RequestParam Long livreId, @RequestParam Long utilisateurId) {
        Emprunt emprunt = empruntService.borrowBook(livreId, utilisateurId);
        return new ResponseEntity<>(emprunt, HttpStatus.CREATED);
    }

    @PostMapping("/return/{empruntId}")
    public ResponseEntity<Emprunt> returnBook(@PathVariable Long empruntId) {
        Emprunt emprunt = empruntService.returnBook(empruntId);
        if (emprunt != null) {
            return new ResponseEntity<>(emprunt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


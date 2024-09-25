package com.bibliotheque.projet.controller;

import com.bibliotheque.projet.model.Livre;
import com.bibliotheque.projet.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    /**
     * Liens de mappage pour LivreController
     *
     * | Méthode HTTP | URI                  | Description                               |
     * |--------------|----------------------|-------------------------------------------|
     * | GET          | /livres               | Récupérer tous les livres                 |
     * | GET          | /livres/{id}          | Récupérer un livre par ID                 |
     * | POST         | /livres               | Créer un nouveau livre                    |
     * | PUT          | /livres/{id}          | Mettre à jour un livre existant           |
     * | DELETE       | /livres/{id}          | Supprimer un livre par ID                 |
     * | GET          | /livres/emprunte      | Récupérer tous les livres empruntés       |
     */

    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Livre livre = livreService.getLivreById(id);
        if (livre != null) {
            return new ResponseEntity<>(livre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addLivre(@RequestBody Livre livre) {
        livreService.addLivre(livre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        Livre existingLivre = livreService.getLivreById(id);
        if (existingLivre != null) {
            livreService.updateLivre(livre);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        Livre livre = livreService.getLivreById(id);
        if (livre != null) {
            livreService.deleteLivre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/emprunte")
    public ResponseEntity<List<Livre>> findEmprunteLivres() {
        List<Livre> emprunteLivres = livreService.findEmprunteLivres();
        return new ResponseEntity<>(emprunteLivres, HttpStatus.OK);
    }

}

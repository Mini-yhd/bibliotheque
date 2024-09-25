package com.bibliotheque.projet.controller;

import com.bibliotheque.projet.model.Utilisateur;
import com.bibliotheque.projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Liens de mappage pour UtilisateurController
     *
     * | Méthode HTTP | URI                                | Description                                     |
     * |--------------|------------------------------------|-------------------------------------------------|
     * | GET          | /utilisateurs                      | Récupérer tous les utilisateurs                 |
     * | GET          | /utilisateurs/{id}                 | Récupérer un utilisateur par ID                 |
     * | GET          | /utilisateurs/username/{username}  | Récupérer un utilisateur par nom d'utilisateur  |
     * | POST         | /utilisateurs                      | Créer un nouvel utilisateur                     |
     * | PUT          | /utilisateurs/{id}                 | Mettre à jour un utilisateur existant           |
     * | DELETE       | /utilisateurs/{id}                 | Supprimer un utilisateur par ID                 |
     */

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<Utilisateur> getUtilisateurByNom(@PathVariable String username) {
        return utilisateurService.getUtilisateurByNom(username);
    }

    @PostMapping
    public void addUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
    }

    @PutMapping("/{id}")
    public void updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateurService.updateUtilisateur(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
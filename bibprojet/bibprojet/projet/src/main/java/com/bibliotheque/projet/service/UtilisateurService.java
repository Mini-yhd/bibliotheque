package com.bibliotheque.projet.service;

import com.bibliotheque.projet.model.Utilisateur;
import com.bibliotheque.projet.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> getUtilisateurByNom(String username) {
        return utilisateurRepository.findByNom(username);
    }

    public void addUtilisateur(Utilisateur Utilisateur) {
        utilisateurRepository.save(Utilisateur);
    }

    public void updateUtilisateur(Utilisateur Utilisateur) {
        utilisateurRepository.save(Utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}

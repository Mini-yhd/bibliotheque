package com.bibliotheque.projet.service;

import com.bibliotheque.projet.model.Emprunt;
import com.bibliotheque.projet.model.Livre;
import com.bibliotheque.projet.model.Utilisateur;
import com.bibliotheque.projet.repository.EmpruntRepository;
import com.bibliotheque.projet.repository.LivreRepository;
import com.bibliotheque.projet.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElseThrow();
    }

    public Emprunt createEmprunt(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    public Emprunt updateEmprunt(Long id, Emprunt emprunt) {
        Emprunt existingEmprunt = getEmpruntById(id);
        existingEmprunt.setId(emprunt.getId());
        existingEmprunt.setDateEmprunt(emprunt.getDateEmprunt());
        existingEmprunt.setDateRetour(emprunt.getDateRetour());
        existingEmprunt.setAdherent(emprunt.getAdherent());
        existingEmprunt.setLivre(emprunt.getLivre());
        return empruntRepository.save(existingEmprunt);
    }

    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    public List<Livre> getAvailableBooks() {
        return livreRepository.findByEmprunteTrue();
    }

    public Emprunt borrowBook(Long livreId, Long utilisateurId) {
        Livre livre = livreRepository.findById(livreId).orElseThrow();
        if (!livre.isEmprunte()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setAdherent(utilisateur);
        emprunt.setDateEmprunt(new Date());
        livre.setEmprunte(false);
        livreRepository.save(livre);
        return empruntRepository.save(emprunt);
    }

    public Emprunt returnBook(Long empruntId) {
        Emprunt emprunt = getEmpruntById(empruntId);
        emprunt.setDateRetour(new Date());
        Livre livre = emprunt.getLivre();
        livre.setEmprunte(true);
        livreRepository.save(livre);
        return empruntRepository.save(emprunt);
    }
}

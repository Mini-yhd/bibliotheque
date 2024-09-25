package com.bibliotheque.projet.service;

import com.bibliotheque.projet.model.Livre;
import com.bibliotheque.projet.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public void addLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElseThrow();
    }

    public void updateLivre(Livre livre) {
        if (livreRepository.existsById(livre.getId())) {
            livreRepository.save(livre);
        } else {
            throw new RuntimeException("Livre not found");
        }
    }

    public void deleteLivre(Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Livre not found");
        }
    }

    public List<Livre> findEmprunteLivres() {
        return livreRepository.findByEmprunteTrue();
    }
}


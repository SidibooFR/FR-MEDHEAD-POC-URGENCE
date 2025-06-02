package fr.medhead.hospital.service;

import fr.medhead.hospital.exceptions.SpecialiteNotFoundException;
import fr.medhead.hospital.model.Hopital;
import fr.medhead.hospital.service.repertoire.HopitalRepository;
import fr.medhead.hospital.service.repertoire.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
public class HopitalServiceImpl implements HopitalService{
    @Autowired
    private final HopitalRepository hopitalRepository;

    @Autowired
    private final SpecialiteRepository specialiteRepository;

    public HopitalServiceImpl(HopitalRepository hopitalRepository, SpecialiteRepository specialiteRepository) {
        this.hopitalRepository = hopitalRepository;
        this.specialiteRepository = specialiteRepository;
    }

    @Override
    public Collection<Hopital> tous() {
        return hopitalRepository.findAll();
    }

    @Override
    public Hopital trouverUnHopitalProcheParSpecialite(String specialiteSouhaite, int origineX, int origineY) throws SpecialiteNotFoundException {
        // Collecter les hopitaux destinations qui possède la spécialité souhaité
        Hopital hRetour;
        Point pointO = new Point(origineX, origineY);

        try {
            // spécialité souhaité
            // lits disponibles
            hRetour = hopitalRepository.findAll()
                    .stream()
                    .filter(h -> h.getSpecialites()
                            .contains(specialiteRepository.findByNom(specialiteSouhaite))) // spécialité souhaité
                    .filter(h -> h.getLitsDisponibles() > 0)
                    .min(Comparator.comparingInt(value ->
                            (int) Point.distance(origineX,origineX,value.getGpsX(), value.getGpsY())))
                    .get();
        } catch (NoSuchElementException e) {
            throw new SpecialiteNotFoundException(specialiteSouhaite);
        }

        return hRetour;
    }
}

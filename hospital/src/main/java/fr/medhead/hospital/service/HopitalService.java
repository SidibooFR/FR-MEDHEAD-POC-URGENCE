package fr.medhead.hospital.service;

import fr.medhead.hospital.model.Hopital;

import java.util.Collection;

public interface HopitalService {
    Collection<Hopital> tous();
    Hopital trouverUnHopitalProcheParSpecialite(String specialiteSouhaite, int origineX, int origineY);
}

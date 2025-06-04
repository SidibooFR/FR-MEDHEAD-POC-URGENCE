package fr.medhead.urgence.service;

import fr.medhead.urgence.model.Urgence;

import java.util.Collection;

public interface UrgenceService {

    Collection<Urgence> tous();

    Urgence nouvelleUrgence(Urgence nouvelleUrgence, String token);

}
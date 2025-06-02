package fr.medhead.urgence.service;

import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.repertoire.UrgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public interface UrgenceService {

    Collection<Urgence> tous();

    Urgence nouvelleUrgence(Urgence nouvelleUrgence);

}
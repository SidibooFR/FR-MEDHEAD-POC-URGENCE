package fr.medhead.urgence.service;

import fr.medhead.urgence.consommationrest.ConsumingHopitalRest;
import fr.medhead.urgence.consommationrest.Hopital;
import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.repository.UrgenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class UrgenceServiceImpl implements UrgenceService {

    @Autowired
    private UrgenceRepository urgenceRepository;

    @Autowired
    private ConsumingHopitalRest hopitalRest;

    @Override
    public Collection<Urgence> tous() {
        return urgenceRepository.findAll();
    }

    @Override
    public Urgence nouvelleUrgence(Urgence nouvelleUrgence, String token) {

        Urgence urgenceComplete = nouvelleUrgence;
        System.out.println("urgence new -> " + nouvelleUrgence);

        // Convertir les coordonnées GPS en String
        String gpsCoord = nouvelleUrgence.getGpsOrigineX() + "," + nouvelleUrgence.getGpsOrigineY();

        Hopital hopitalDestination = this.hopitalRest
                .trouverUnHopitalProcheParSpecialite(
                        nouvelleUrgence.getSpecialiteSouhaite(),
                        gpsCoord, // Converti en String
                        token);

        log.debug("retour du service hopital --> " + hopitalDestination.toString());

        urgenceComplete.setHopitalDestinationId(hopitalDestination.getId());
        urgenceComplete.setNomHopitalDestination(hopitalDestination.getNomHopital());
        // 2- reserver un lit pour le patient à transférer
        urgenceComplete.setReservationId(0);

        log.debug("retour urgence complété --> " + hopitalDestination.toString());

        System.out.println("urgence complété -> " + urgenceComplete);

        // 3 - sauvegarder l'urgence
        return this.urgenceRepository.save(urgenceComplete);
    }
}

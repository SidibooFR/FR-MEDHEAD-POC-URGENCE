package fr.medhead.urgence.service;

import fr.medhead.urgence.consommationrest.ConsumingHopitalRest;
import fr.medhead.urgence.consommationrest.Hopital;
import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.repository.UrgenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class UrgenceServiceImpl implements UrgenceService {

    private static final Logger LOG = LoggerFactory.getLogger(UrgenceServiceImpl.class);

    @Autowired
    private UrgenceRepository urgenceRepository;

    @Autowired
    private ConsumingHopitalRest hopitalRest;

    @Override
    public Collection<Urgence> tous() {
        return urgenceRepository.findAll();
    }

    @Override
    public Urgence nouvelleUrgence(Urgence nouvelleUrgence) {

        Urgence urgenceComplete = nouvelleUrgence;
        System.out.println("urgence new -> " + nouvelleUrgence);
        Hopital hopitalDestination = this.hopitalRest
                .trouverUnHopitalProcheParSpecialite(nouvelleUrgence.getSpecialiteSouhaite(),
                        nouvelleUrgence.getGpsOrigineX(), nouvelleUrgence.getGpsOrigineY());

        LOG.debug("retour du service hopital --> " + hopitalDestination.toString());

        urgenceComplete.setHopitalDestinationId(hopitalDestination.getId());
        urgenceComplete.setNomHopitalDestination(hopitalDestination.getNomHopital());
        // 2- reserver un lit pour le patient à transférer
        urgenceComplete.setReservationId(0);

        LOG.debug("retour urgence complété --> " + hopitalDestination.toString());

        System.out.println("urgence complété -> " + urgenceComplete);

        // 3 - sauvegarder l'urgence
        return this.urgenceRepository.save(urgenceComplete);
    }
}

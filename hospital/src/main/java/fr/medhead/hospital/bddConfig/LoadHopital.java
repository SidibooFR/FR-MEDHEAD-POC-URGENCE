package fr.medhead.hospital.bddConfig;

import fr.medhead.hospital.model.Hopital;
import fr.medhead.hospital.model.Specialite;
import fr.medhead.hospital.service.repertoire.HopitalRepository;
import fr.medhead.hospital.service.repertoire.SpecialiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadHopital {

    private static final Logger log = LoggerFactory.getLogger(LoadHopital.class);

    @Bean
    CommandLineRunner initDatabase(HopitalRepository hopital, SpecialiteRepository specialite) {
        return args -> {
            log.info("Preloading 1 " + specialite.save(new Specialite("medecine generale","cardiologie")));
            log.info("Preloading 1 " + specialite.save(new Specialite("pathologie", "immunologie")));
            log.info("Preloading 1 " + specialite.save(new Specialite("pathologie", "neuropathologie diagnostique")));

            List<Specialite> hFB = new ArrayList<>();
            List<Specialite> hJC = new ArrayList<>();
            List<Specialite> hBB = new ArrayList<>();

            hFB.add(specialite.findByNom("cardiologie"));
            hFB.add(specialite.findByNom("immunologie"));

            hJC.add(specialite.findByNom("immunologie"));

            hBB.add(specialite.findByNom("immunologie"));
            hBB.add(specialite.findByNom("neuropathologie diagnostique"));

            log.info("Preloading 2 " + hopital.save(new Hopital("Hopital Fred Brooks",1,1,2, hFB)));
            log.info("Preloading 2 " + hopital.save(new Hopital("Hopital Julia Crusher",2,2, 0, hJC)));
            log.info("Preloading 2 " + hopital.save(new Hopital("Hopital Beverly Bashir",4,6, 5, hBB)));
        };
    }
}

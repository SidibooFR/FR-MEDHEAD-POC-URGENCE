package fr.medhead.hospital.service.repertoire;

import fr.medhead.hospital.model.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {
    Hopital findByNomHopital(String nomHopital);
}

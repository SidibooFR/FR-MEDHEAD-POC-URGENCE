package fr.medhead.hospital.repository;

import fr.medhead.hospital.model.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HopitalRepository extends JpaRepository<Hopital, Long> {
    Hopital findByNomHopital(String nomHopital);
}
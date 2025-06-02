package fr.medhead.hospital.service.repertoire;

import fr.medhead.hospital.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Integer> {
    Specialite findByNom(String nom);
}

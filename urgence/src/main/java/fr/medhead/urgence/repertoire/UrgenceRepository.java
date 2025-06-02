package fr.medhead.urgence.repertoire;

import fr.medhead.urgence.model.Urgence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrgenceRepository extends JpaRepository<Urgence, Long> {

}

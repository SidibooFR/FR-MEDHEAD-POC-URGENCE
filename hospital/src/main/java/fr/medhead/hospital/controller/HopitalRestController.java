package fr.medhead.hospital.controller;

import fr.medhead.hospital.model.Hopital;
import fr.medhead.hospital.model.Specialite;
import fr.medhead.hospital.service.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class HopitalRestController {
    @Autowired
    private HopitalService hopitalService;

    @GetMapping("/hopitaux")
    Collection<Hopital> tous() {
        return this.hopitalService.tous();
    }

    @GetMapping("/hopitaux/{specialiteSouhaite}/{origineX}/{origineY}")
    Hopital trouverUnHopitalProcheParSpecialite(@PathVariable String specialiteSouhaite, @PathVariable int origineX,
            @PathVariable int origineY) {
        return (Hopital) hopitalService.trouverUnHopitalProcheParSpecialite(specialiteSouhaite, origineX, origineY);
    }
}

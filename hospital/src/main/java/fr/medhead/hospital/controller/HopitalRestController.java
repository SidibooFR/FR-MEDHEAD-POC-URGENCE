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
    private final HopitalService hopitalServiceImpl;

    public HopitalRestController(HopitalService hopitalServiceImpl) {
        this.hopitalServiceImpl = hopitalServiceImpl;
    }

    @GetMapping ("/hopitaux")
    Collection<Hopital> tous() {
        return this.hopitalServiceImpl.tous();
    }

    @GetMapping("/hopitaux/{specialiteSouhaite}/{origineX}/{origineY}")
    Hopital trouverUnHopitalProcheParSpecialite(@PathVariable String specialiteSouhaite, @PathVariable int origineX, @PathVariable int origineY) {
        return (Hopital) hopitalServiceImpl.trouverUnHopitalProcheParSpecialite(specialiteSouhaite, origineX, origineY);
    }
}

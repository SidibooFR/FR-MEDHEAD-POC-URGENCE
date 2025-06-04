package fr.medhead.urgence.controller;

import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.service.UrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UrgenceRestController {
    @Autowired
    private UrgenceService urgenceService;

    @GetMapping("/urgences")
    public Collection<Urgence> tous() {
        return urgenceService.tous();
    }

    @PostMapping(path = "/urgences/add")
    public Urgence nouvelleUrgence(@RequestBody Urgence nouvelleUrgence, @RequestHeader("Authorization") String token) {
        return urgenceService.nouvelleUrgence(nouvelleUrgence, token);
    }
}

package fr.medhead.urgence.controller;

import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.service.UrgenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Collection;

@RestController
public class UrgenceRestController {
    @Autowired
    private UrgenceService urgenceService;

    public UrgenceRestController(UrgenceService urgenceService) {
        this.urgenceService =urgenceService;
    }

    @GetMapping("/urgences")
    public Collection<Urgence> tous() {
        return urgenceService.tous();
    }

    @PostMapping(path = "/urgences/add"
            , consumes=MediaType.APPLICATION_JSON_VALUE
            , produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Urgence nouvelleUrgence(@RequestBody Urgence nouvelleUrgence) {
        Urgence u =urgenceService.nouvelleUrgence(nouvelleUrgence);
        return u;
    }
}

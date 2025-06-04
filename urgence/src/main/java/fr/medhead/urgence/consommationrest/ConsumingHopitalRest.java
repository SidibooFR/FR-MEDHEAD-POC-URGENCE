package fr.medhead.urgence.consommationrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumingHopitalRest {

    @Autowired
    private final RestTemplate restTemplate;

    public ConsumingHopitalRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Hopital trouverUnHopitalProcheParSpecialite(
            String specialite,
            String gps,
            String token) {

        String[] parts = gps.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return restTemplate.exchange(
                "http://localhost:8081/hopitaux/{specialite}/{x}/{y}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Hopital.class,
                specialite,
                x,
                y).getBody();
    }
}

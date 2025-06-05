package fr.medhead.urgence.consommationrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hopital {
    private long id;
    private String nomHopital;
    private int gpsX; // coordonné GPS de l'hopital
    private int gpsY; // coordonné GPS de l'hopital
    private int litsDisponibles;
    private List<Specialite> specialites;

    public Hopital() {}

    public Hopital(String nomHopital, int gpsX, int gpsY, int litsDisponibles, List<Specialite> specialites) {
        this.nomHopital = nomHopital;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.litsDisponibles = litsDisponibles;
        this.specialites = specialites;
    }
}

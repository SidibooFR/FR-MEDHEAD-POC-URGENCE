package fr.medhead.hospital.model;

import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Hopital {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nomHopital;
    private int gpsX; // coordonné GPS de l'hopital
    private int gpsY; // coordonné GPS de l'hopital
    private int litsDisponibles;

    @ManyToMany()
    @JoinTable(name = "hopital_specialite"
            , joinColumns = @JoinColumn(name = "hopital_id")
            , inverseJoinColumns = @JoinColumn(name = "specialite_id"))
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

package fr.medhead.hospital.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Specialite {

    @Id
    @GeneratedValue
    private int id;
    private String groupe;
    private String nom;

    public Specialite() {/*CONSTRUCTEUR*/}

    public Specialite(String groupe, String nom) {
        this.groupe = groupe;
        this.nom = nom;
    }

}

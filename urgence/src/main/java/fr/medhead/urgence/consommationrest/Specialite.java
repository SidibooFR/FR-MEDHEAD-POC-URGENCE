package fr.medhead.urgence.consommationrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Specialite {
    private int id;
    private String groupe;
    private String nom;

    public Specialite() {/*CONSTRUCTEUR*/}

    public Specialite(String groupe, String nom) {
        this.groupe = groupe;
        this.nom = nom;
    }
}

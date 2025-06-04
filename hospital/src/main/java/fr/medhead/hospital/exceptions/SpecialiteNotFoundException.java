package fr.medhead.hospital.exceptions;

public class SpecialiteNotFoundException extends RuntimeException{
    public SpecialiteNotFoundException(String nom) {
        super("La specialite suivant n'a pas ete trouve : " + nom);
    }
}

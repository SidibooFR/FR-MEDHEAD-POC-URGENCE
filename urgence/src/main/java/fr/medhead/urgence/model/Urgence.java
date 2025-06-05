package fr.medhead.urgence.model;

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
public class Urgence {

    @Id @GeneratedValue
    private long id;
    private long patientId;
    private String specialiteSouhaite;
    private int gpsOrigineX;
    private int gpsOrigineY;
    private long hopitalDestinationId;
    private String nomHopitalDestination;
    private long reservationId;

    public Urgence(long patientId, String specialiteSouhaite,int origineX, int origineY) {
        this.patientId = patientId;
        this.specialiteSouhaite = specialiteSouhaite;
        this.gpsOrigineX = origineX;
        this.gpsOrigineY = origineY;
    }

    public Urgence(long patientId
            , String specialiteSouhaite
            , int gpsOrigineX
            , int gpsOrigineY
            , long hopitalDestinationId
            , String nomHopitalDestination
            , long reservationId) {
        this.patientId = patientId;
        this.specialiteSouhaite = specialiteSouhaite;
        this.gpsOrigineX = gpsOrigineX;
        this.gpsOrigineY = gpsOrigineY;
        this.hopitalDestinationId = hopitalDestinationId;
        this.nomHopitalDestination = nomHopitalDestination;
        this.reservationId = reservationId;
    }
}

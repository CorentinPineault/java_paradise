package com.formation.app;

public class Trip {
    private long Id;
    private long lieuDepart;
    private long lieuArrivee;
    private float prix;

    public Trip() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(long lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public long getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(long lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}

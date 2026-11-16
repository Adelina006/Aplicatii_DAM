package com.example.model_restaurant;

import java.io.Serializable;
import java.util.Date;

public class Restaurant implements Serializable {
    private String nume;
    private int nrLocuri;
    private boolean rezervat;
    private String vibe;
    private boolean imiPlace;
    private Date data;

    public Restaurant(String nume, int nrLocuri, boolean rezervat, String vibe, boolean imiPlace, Date data) {
        this.nume = nume;
        this.nrLocuri = nrLocuri;
        this.rezervat = rezervat;
        this.vibe = vibe;
        this.imiPlace = imiPlace;
        this.data = data;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public boolean isRezervat() {
        return rezervat;
    }

    public void setRezervat(boolean rezervat) {
        this.rezervat = rezervat;
    }

    public String getVibe() {
        return vibe;
    }

    public void setVibe(String vibe) {
        this.vibe = vibe;
    }

    public boolean isImiPlace() {
        return imiPlace;
    }

    public void setImiPlace(boolean imiPlace) {
        this.imiPlace = imiPlace;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "nume='" + nume + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", rezervat=" + rezervat +
                ", vibe='" + vibe + '\'' +
                ", imiPlace=" + imiPlace +
                ", data=" + data +
                '}';
    }
}

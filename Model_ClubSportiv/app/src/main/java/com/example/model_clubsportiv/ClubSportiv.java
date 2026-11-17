package com.example.model_clubsportiv;

import java.io.Serializable;
import java.util.Date;

public class ClubSportiv implements Serializable {

    private Date dataCreare;
    private String sport;
    private boolean costa;
    private boolean imiPlace;
    private int nrLocuri;

    public ClubSportiv(Date dataCreare, String sport, boolean costa, boolean imiPlace, int nrLocuri) {
        this.dataCreare = dataCreare;
        this.sport = sport;
        this.costa = costa;
        this.imiPlace = imiPlace;
        this.nrLocuri = nrLocuri;
    }

    public Date getDataCreare() {
        return dataCreare;
    }

    public void setDataCreare(Date dataCreare) {
        this.dataCreare = dataCreare;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public boolean isCosta() {
        return costa;
    }

    public void setCosta(boolean costa) {
        this.costa = costa;
    }

    public boolean isImiPlace() {
        return imiPlace;
    }

    public void setImiPlace(boolean imiPlace) {
        this.imiPlace = imiPlace;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    @Override
    public String toString() {
        return "ClubSportiv{" +
                "dataCreare=" + dataCreare +
                ", sport='" + sport + '\'' +
                ", costa=" + costa +
                ", imiPlace=" + imiPlace +
                ", nrLocuri=" + nrLocuri +
                '}';
    }
}

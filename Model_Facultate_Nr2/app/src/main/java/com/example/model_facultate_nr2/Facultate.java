package com.example.model_facultate_nr2;

import java.io.Serializable;

public class Facultate implements Serializable {

    private String nume;
    private boolean areDoctorat;
    private boolean areMaster;
    private int nrSpecializari;
    private String specializare;
    private double taxa;

    public Facultate(String nume, boolean areDoctorat, boolean areMaster, int nrSpecializari, String specializare, double taxa) {
        this.nume = nume;
        this.areDoctorat = areDoctorat;
        this.areMaster = areMaster;
        this.nrSpecializari = nrSpecializari;
        this.specializare = specializare;
        this.taxa = taxa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean isAreDoctorat() {
        return areDoctorat;
    }

    public void setAreDoctorat(boolean areDoctorat) {
        this.areDoctorat = areDoctorat;
    }

    public boolean isAreMaster() {
        return areMaster;
    }

    public void setAreMaster(boolean areMaster) {
        this.areMaster = areMaster;
    }

    public int getNrSpecializari() {
        return nrSpecializari;
    }

    public void setNrSpecializari(int nrSpecializari) {
        this.nrSpecializari = nrSpecializari;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return "Facultate{" +
                "nume='" + nume + '\'' +
                ", areDoctorat=" + areDoctorat +
                ", areMaster=" + areMaster +
                ", nrSpecializari=" + nrSpecializari +
                ", specializare='" + specializare + '\'' +
                ", taxa=" + taxa +
                '}';
    }
}

package com.example.tierliste3.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class TiersListe implements Serializable {

    private String titre;

    private ArrayList<Film> base;
    private ArrayList<Film> tierS;
    private ArrayList<Film> tierF;

    public TiersListe(String titre,ArrayList<Film> base,ArrayList<Film> tierS, ArrayList<Film> tierF){
        this.titre = titre;
        this.base = base;
        this.tierS = tierS;
        this.tierF = tierF;
    }
    public ArrayList<Film> getBase() {
        return base;
    }

    public void setBase(ArrayList<Film> base) {
        this.base = base;
    }

    public ArrayList<Film> getTierS() {
        return tierS;
    }

    public void setTierS(ArrayList<Film> tierS) {
        this.tierS = tierS;
    }

    public ArrayList<Film> getTierF() {
        return tierF;
    }

    public void setTierF(ArrayList<Film> tierF) {
        this.tierF = tierF;
    }



    public String getTitre(){
        return titre;
    }

    public void setTitre(String t){
        this.titre = t;
    }



}

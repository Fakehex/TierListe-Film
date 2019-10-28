package com.example.tierliste3.Controllers;

public class TiersListe {

    private String titre;
    private int id;
    //Type de la Liste (Classique=0/Theme=1) ?
    //private boolean type;

    public String getTitre(){
        return titre;
    }

    public void setTitre(String t){
        this.titre = t;
    }

}

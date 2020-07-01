package com.example.myperfectteam;

public class Fil {
    private int id;
    private String usuari;
    private String tema;

    public Fil(int id, String usuari, String tema) {
        this.id = id;
        this.usuari = usuari;
        this.tema = tema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}

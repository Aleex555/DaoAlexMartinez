package com.project;

import java.util.ArrayList;

public class ObjSoftware {
    private int id;
    private String nom;
    private int any;
    private ArrayList<Integer> llenguatges;

    public ObjSoftware(int id, String nom, int any, ArrayList<Integer> llenguatges) {
        this.id = id;
        this.nom = nom;
        this.any = any;
        this.llenguatges = new ArrayList<>(llenguatges);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public ArrayList<Integer> getLlenguatges() {
        return llenguatges;
    }

    public void setLlenguatges(ArrayList<Integer> llenguatges) {
        this.llenguatges = llenguatges;
    }

    public void addLlenguatge(int idLlenguatge) {
        this.llenguatges.add(idLlenguatge);
    }

    public void removeLlenguatge(int idLlenguatge) {
        this.llenguatges.remove(Integer.valueOf(idLlenguatge));
    }

    @Override
    public String toString() {
        return "ObjSoftware{id=" + id + ", nom='" + nom + '\'' + ", any=" + any + ", llenguatges=" + llenguatges + '}';
    }
}

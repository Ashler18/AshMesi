package com.example.Classes;

public class Voiture {
    private int id;
    private final String marque;
    private final String modele;
    private final int annee;
    private final String description;
    private final String cheminImage;
    private final double prix;

    // Constructeur
    public Voiture(int id, String marque, String modele, int annee, String description, String cheminImage,
            double prix) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.description = description;
        this.cheminImage = cheminImage;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", annee=" + annee +
                ", description='" + description + '\'' +
                ", cheminImage='" + cheminImage + '\'' +
                ", prix=" + prix +
                '}';
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getAnnee() {
        return annee;
    }

    public String getDescription() {
        return description;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public double getPrix() {
        return prix;
    }
}
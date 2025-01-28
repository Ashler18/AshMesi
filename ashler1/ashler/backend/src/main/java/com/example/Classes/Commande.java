package com.example.Classes;



public class Commande {
    private final int id;
    private final int idVoiture;
    private final int idUtilisateur;
    private final String dateCommande;
    private  final double prix;

    // Constructeur
    public Commande(int id, int idVoiture, int idUtilisateur, String dateCommande, double prix) {
        this.id = id;
        this.idVoiture = idVoiture;
        this.idUtilisateur = idUtilisateur;
        this.dateCommande = dateCommande;
        this.prix = prix;
    }

    // Getters
    public int getId() { return id; }
    public int getIdVoiture() { return idVoiture; }
    public int getIdUtilisateur() { return idUtilisateur; }
    public String getDateCommande() { return dateCommande; }
    public double getPrix() { return prix; }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", idVoiture=" + idVoiture +
                ", idUtilisateur=" + idUtilisateur +
                ", dateCommande='" + dateCommande + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }
}
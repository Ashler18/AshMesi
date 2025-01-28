package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Classes.Commande;

public class CommandeDAO {
    private final Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterCommande(Commande commande) throws SQLException {
        String sql = "INSERT INTO commandes (voiture_id, utilisateur_id, date_Commande) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commande.getIdVoiture());
            statement.setInt(2, commande.getIdUtilisateur());
            statement.setString(3, commande.getDateCommande());
            statement.executeUpdate();
        }
    }

    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commandes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Commande commande = new Commande(
                        resultSet.getInt("ID"),
                        resultSet.getInt("voiture_id"),
                        resultSet.getInt("utilisateur_id"),
                        resultSet.getString("Date_commande"),
                        resultSet.getDouble("prix")
                );
                commandes.add(commande);
            }
        }
        return commandes;
    }
     public void addCommande(int idUtilisateur, int idVoiture, String dateCommande, double prix) {
        String sql = "INSERT INTO commandes (utilisateur_id, voiture_id, date_commande, prix) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Définissez les valeurs pour chaque paramètre
            stmt.setInt(1, idUtilisateur);
            stmt.setInt(2, idVoiture);
            stmt.setDate(3, Date.valueOf(dateCommande));
            stmt.setDouble(4, prix);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Commande ajoutée avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout de la commande.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }
}
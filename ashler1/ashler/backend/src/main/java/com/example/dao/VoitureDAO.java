package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Classes.Voiture;

public class VoitureDAO {
    private Connection connection = null;
    
        public VoitureDAO(Connection connection2) {
            this.connection = connection2;
    }

    public VoitureDAO(org.eclipse.jetty.client.api.Connection connection2) {
        //TODO Auto-generated constructor stub
    }

    public void addVoiture(Voiture voiture) throws SQLException {
        String sql = "INSERT INTO voitures (Marque, Modele, Annee, Description, Chemin_image, Prix) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, voiture.getMarque());
            statement.setString(2, voiture.getModele());
            statement.setInt(3, voiture.getAnnee());
            statement.setString(4, voiture.getDescription());
            statement.setString(5, voiture.getCheminImage());
            statement.setDouble(6, voiture.getPrix());
            statement.executeUpdate();
        }
    }

    public Voiture getVoitureById(int id) {
        Voiture voiture = null;
        String sql = "SELECT * FROM voitures WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Remplacez le paramètre avec l'ID
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Si une voiture est trouvée, créez l'objet Voiture
                voiture = new Voiture(
                    rs.getInt("id"),
                    rs.getString("marque"),
                    rs.getString("modele"),
                    rs.getInt("annee"),
                    rs.getString("description"),
                    rs.getString("chemin_image"),
                    rs.getDouble("prix")
                );
            } else {
                System.out.println("Aucune voiture trouvée avec l'ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la voiture : " + e.getMessage());
        }
        return voiture; // Retournez l'objet Voiture ou null si non trouvé
    }

    public List<Voiture> getAllVoitures() throws SQLException {
        List<Voiture> voitures = new ArrayList<>();
        String sql = "SELECT * FROM voitures";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Voiture voiture = new Voiture(
                        resultSet.getInt("id"),
                        resultSet.getString("marque"),
                        resultSet.getString("modele"),
                        resultSet.getInt("annee"),
                        resultSet.getString("description"),
                        resultSet.getString("chemin_image"),
                        resultSet.getDouble("prix")
                );
                voitures.add(voiture);
            }
        }
        return voitures;
    }

    public void updateVoiture(Voiture voiture) {
        String sql = "UPDATE voitures SET marque = ?, modele = ?, annee = ?, description = ?, cheminImage = ?, prix = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Définissez les valeurs pour chaque paramètre
            stmt.setString(1, voiture.getMarque());
            stmt.setString(2, voiture.getModele());
            stmt.setInt(3, voiture.getAnnee());
            stmt.setString(4, voiture.getDescription());
            stmt.setString(5, voiture.getCheminImage());
            stmt.setDouble(6, voiture.getPrix());
            stmt.setInt(7, voiture.getId()); // Assurez-vous que la voiture a un ID valide

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Voiture mise à jour avec succès !");
            } else {
                System.out.println("Aucune voiture trouvée avec cet ID : " + voiture.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la voiture : " + e.getMessage());
        }
    }

    public void deleteVoiture(int id) {
        String sql = "DELETE FROM voitures WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Remplacez le paramètre avec l'ID de la voiture à supprimer

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Voiture supprimée avec succès !");
            } else {
                System.out.println("Aucune voiture trouvée avec cet ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la voiture : " + e.getMessage());
        }
    }

}
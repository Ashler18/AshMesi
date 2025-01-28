package com.example.dao;

import com.example.Classes.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilisateurDAO {
    private final Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurDAO.class);

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getEmail());
            statement.setString(3, utilisateur.getMotDePasse());
            statement.executeUpdate();
        }
    }

    public List<Utilisateur> getAllUtilisateurs() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT id, nom, email, mot_de_passe FROM utilisateurs";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("mot_de_passe"));
                utilisateurs.add(utilisateur);
            }
        }
        return utilisateurs;
    }

    public Utilisateur getUtilisateurById(int id) throws SQLException {
        String query = "SELECT id, nom, email, mot_de_passe FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            logger.debug("Executing query: " + query + " with ID: " + id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"));
            }
            logger.debug("No utilisateur found with ID: " + id);
            return null;
        }
    }
}

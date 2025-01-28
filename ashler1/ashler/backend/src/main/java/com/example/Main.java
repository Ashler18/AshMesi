package com.example;

import static spark.Spark.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.example.Classes.Commande;
import com.example.Classes.Utilisateur;
import com.example.Classes.Voiture;
import com.example.dao.CommandeDAO;
import com.example.dao.UtilisateurDAO;
import com.example.dao.VoitureDAO;
import com.google.gson.Gson;
import spark.Spark;

public class Main {
  private static final Gson gson = new Gson();
  private static final String INTERNAL_SERVER_ERROR = "Erreur interne du serveur : ";
  private static final String IMAGES_BASE_PATH = "src/main/resources/";

  public static void main(String[] args) {
    configureServer();
    configureCORS();
    setupRoutes();
  }

  private static void configureServer() {
    port(4567);
  }

  private static void configureCORS() {
    options("/*", (request, response) -> {
      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }

      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }
      return "OK";
    });

    before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  }

  private static void setupRoutes() {
    // Routes Voitures
    setupVoituresRoutes();

    // Routes Utilisateurs
    setupUtilisateursRoutes();

    // Routes Commandes
    setupCommandesRoutes();

    // Route Images
    setupImagesRoutes();
  }

  private static void setupVoituresRoutes() {
    // GET all voitures
    get("/voitures", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        res.type("application/json");
        List<Voiture> voitures = voitureDAO.getAllVoitures();
        return gson.toJson(voitures);
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // POST new voiture
    post("/voitures", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        Voiture nouvelleVoiture = gson.fromJson(req.body(), Voiture.class);
        voitureDAO.addVoiture(nouvelleVoiture);
        res.status(201);
        return "Voiture ajoutée avec succès";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // GET voiture by ID
    get("/voitures/:id", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        Voiture voiture = voitureDAO.getVoitureById(Integer.parseInt(req.params(":id")));
        if (voiture != null) {
          res.type("application/json");
          return gson.toJson(voiture);
        }
        res.status(404);
        return "Voiture non trouvée";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // PUT update voiture
    put("/voitures/:id", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        Voiture voiture = gson.fromJson(req.body(), Voiture.class);
        voiture.setId(Integer.parseInt(req.params(":id")));
        voitureDAO.updateVoiture(voiture);
        return "Voiture mise à jour avec succès";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // DELETE voiture
    delete("/voitures/:id", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        voitureDAO.deleteVoiture(Integer.parseInt(req.params(":id")));
        return "Voiture supprimée avec succès";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });
  }

  private static void setupUtilisateursRoutes() {
    // GET all utilisateurs
    get("/utilisateurs", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);
        res.type("application/json");
        return gson.toJson(utilisateurDAO.getAllUtilisateurs());
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // POST new utilisateur
    post("/utilisateurs", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);
        Utilisateur nouvelUtilisateur = gson.fromJson(req.body(), Utilisateur.class);
        utilisateurDAO.ajouterUtilisateur(nouvelUtilisateur);
        res.status(201);
        return "Utilisateur ajouté avec succès";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // GET utilisateur by ID
    get("/utilisateurs/:id", (req, res) -> {     
      try (Connection connection = Database.getConnection()) {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);
        int id = Integer.parseInt(req.params(":id"));
        System.out.println("Fetching utilisateur with ID: " + id);
        Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(Integer.parseInt(req.params(":id")));
        if (utilisateur != null) {
          res.type("application/json");
          return gson.toJson(utilisateur);
        }
        res.status(404);
        return "Utilisateur non trouvé";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });
  }

  private static void setupCommandesRoutes() {
    // GET all commandes
    get("/commandes", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        CommandeDAO commandeDAO = new CommandeDAO(connection);
        res.type("application/json");
        return gson.toJson(commandeDAO.getAllCommandes());
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });

    // POST new commande
    post("/commandes", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        CommandeDAO commandeDAO = new CommandeDAO(connection);
        Commande nouvelleCommande = gson.fromJson(req.body(), Commande.class);
        commandeDAO.addCommande(
            nouvelleCommande.getIdUtilisateur(),
            nouvelleCommande.getIdVoiture(),
            nouvelleCommande.getDateCommande(),
            nouvelleCommande.getPrix());
        res.status(201);
        return "Commande ajoutée avec succès";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });
  }

  private static void setupImagesRoutes() {
    get("/images/voitures/:id", (req, res) -> {
      try (Connection connection = Database.getConnection()) {
        VoitureDAO voitureDAO = new VoitureDAO(connection);
        Voiture voiture = voitureDAO.getVoitureById(Integer.parseInt(req.params(":id")));

        if (voiture != null && voiture.getCheminImage() != null) {
          File imageFile = new File(IMAGES_BASE_PATH + voiture.getCheminImage());
          if (imageFile.exists()) {
            res.type("image/jpeg");
            return new FileInputStream(imageFile);
          }
          res.status(404);
          return "Image non trouvée";
        }
        res.status(404);
        return "Aucune image associée à cette voiture";
      } catch (SQLException e) {
        res.status(500);
        return INTERNAL_SERVER_ERROR + e.getMessage();
      }
    });
  }
}
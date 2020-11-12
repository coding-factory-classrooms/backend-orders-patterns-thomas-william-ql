package org.example;

import org.example.controllers.CommandeController;
import org.example.controllers.SystemeCommandeController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.Commande;
import org.example.models.Plats;
import org.example.models.SystemeCommande;
import spark.Spark;

import java.util.HashMap;

public class App {


    public static void main(String[] args) {
        initialize();
        SystemeCommande systemeCommande = new SystemeCommande();
        CommandeController commandeController = new CommandeController(systemeCommande);
        SystemeCommandeController systemeCommandeController = new SystemeCommandeController(systemeCommande);



        Commande commande = new Commande();
        commande.addPlats(new Plats());
        commande.setOnCommandeChangeListener(systemeCommande);
        systemeCommande.addOrder(commande);

        commande = new Commande();
        commande.addPlats(new Plats());
        commande.setOnCommandeChangeListener(systemeCommande);
        systemeCommande.addOrder(commande);

        commande = new Commande();
        commande.addPlats(new Plats());
        commande.setOnCommandeChangeListener(systemeCommande);
        systemeCommande.addOrder(commande);



        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });
        Spark.get("/dashboard", (req, res) -> {
            return systemeCommandeController.list(req, res);
        });
        Spark.get("/action/:id", (req, res) -> {
            return systemeCommandeController.commandHistory(req, res);
        });

        Spark.get("/commande/:id", (req, res) -> {
            return commandeController.changeCommande(req, res);
        });

        Spark.get("/customer", (req, res) -> {
            return Template.render("customer.html", new HashMap<>());
        });
        Spark.get("/order", (req, res) -> {
            return Template.render("order.html", new HashMap<>());
        });
    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);



    }
}

package org.example;

import org.example.controllers.SystemeCommandeController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.SystemeCommande;
import spark.Spark;

import java.util.HashMap;

public class App {


    public static void main(String[] args) {
        initialize();
        SystemeCommande systemeCommande = new SystemeCommande();

        SystemeCommandeController systemeCommandeController = new SystemeCommandeController(systemeCommande);

        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });
        Spark.get("/dashboard", (req, res) -> {
            return systemeCommandeController.list(req, res);
            //return Template.render("dashboard.html", new HashMap<>());
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

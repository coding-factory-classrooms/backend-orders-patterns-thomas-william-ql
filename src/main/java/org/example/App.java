package org.example;

import org.example.controllers.OrderController;
import org.example.controllers.SystemeCommandeController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.LogSystem;
import org.example.models.SystemeCommande;
import spark.Spark;

import java.util.HashMap;

public class App {


    public static void main(String[] args) {
        initialize();
        LogSystem logSystem = new LogSystem();
        SystemeCommande systemeCommande = new SystemeCommande(logSystem);
        OrderController orderController = new OrderController(systemeCommande);
        SystemeCommandeController systemeCommandeController = new SystemeCommandeController(systemeCommande);





        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });
        Spark.get("/dashboard", (req, res) -> {
            return systemeCommandeController.list(req, res);
        });
        Spark.get("/action/:id", (req, res) -> {
            return systemeCommandeController.commandHistory(req, res);
        });

        Spark.get("/order/:id", (req, res) -> {
            return orderController.changeOrder(req, res);
        });
        Spark.get("/create/:id", (req, res) -> {
            return systemeCommandeController.createCommande(req, res);
        });
        Spark.get("/history", (req, res) -> {
            return systemeCommandeController.systemHistory(req, res);
        });

/*        Spark.get("/customer", (req, res) -> {
            return Template.render("customer.html", new HashMap<>());
        });
        Spark.get("/order", (req, res) -> {
            return Template.render("order.html", new HashMap<>());
        });*/
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

package org.example.controllers;

import org.example.core.Template;
import org.example.models.Commande;
import org.example.models.Plats;
import org.example.models.SystemeCommande;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class SystemeCommandeController {

    private SystemeCommande systemeCommande;

    public SystemeCommandeController(SystemeCommande systemeCommande){
        this.systemeCommande = systemeCommande;
    }


    public String list(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        Commande commande = new Commande();
        commande.addPlats(new Plats());
        systemeCommande.addOrder(commande);

        commande = new Commande();
        commande.addPlats(new Plats());
        systemeCommande.addOrder(commande);

        commande = new Commande();
        commande.addPlats(new Plats());
        systemeCommande.addOrder(commande);

        model.put("commands", systemeCommande.getOrderList());
        return Template.render("dashboard.html", model);
    }
}

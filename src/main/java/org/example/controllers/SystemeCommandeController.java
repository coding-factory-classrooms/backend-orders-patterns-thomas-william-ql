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

    private final SystemeCommande systemeCommande;

    public SystemeCommandeController(SystemeCommande systemeCommande){
        this.systemeCommande = systemeCommande;
    }


    public String list(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        model.put("commands", systemeCommande.getOrderList());
        System.out.println(systemeCommande.getHistory());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }


    public String commandHistory(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        int id = Integer.parseInt(request.params(":id"));
        int index = id - 1;


        Commande commande = systemeCommande.getCommande(index);

        String state = request.queryParamOrDefault("action", "");
        if (state.equals("undo"))  commande.restore(commande.getCommandHistory().undo());
        if (state.equals("redo"))  commande.restore(commande.getCommandHistory().redo());


        model.put("commands", systemeCommande.getOrderList());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }
}

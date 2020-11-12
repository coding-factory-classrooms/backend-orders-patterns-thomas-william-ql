package org.example.controllers;

import org.example.core.Template;
import org.example.models.Commande;
import org.example.models.SystemeCommande;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CommandeController {

    private final SystemeCommande systemeCommande;

    public CommandeController(SystemeCommande systemeCommande) {
        this.systemeCommande = systemeCommande;
    }

    public String changeCommande(Request request, Response response) {

        int id = Integer.parseInt(request.params(":id"));
        int index = id - 1;


        Commande commande = systemeCommande.getCommande(index);

        String state = request.queryParamOrDefault("state", "");
        if (state.equals("ENCOURS")) commande.setState(Commande.State.ENCOURS);
        if (state.equals("NOUVEAU")) commande.setState(Commande.State.NOUVEAU);
        if (state.equals("TERMINEE")) commande.setState(Commande.State.TERMINEE);
        if (state.equals("ANNULEE")) commande.setState(Commande.State.ANNULEE);

        Map<String, Object> model = new HashMap<>();

        model.put("orderID", id);
        model.put("commande", commande);
        return Template.render("order.html", model);
    }

}

package org.example.controllers;

import org.example.core.Template;
import org.example.models.Order;
import org.example.models.SystemeCommande;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrderController {

    private final SystemeCommande systemeCommande;

    public OrderController(SystemeCommande systemeCommande) {
        this.systemeCommande = systemeCommande;
    }

    public String changeOrder(Request request, Response response) {

        int id = Integer.parseInt(request.params(":id"));
        int index = id - 1;


        Order order = systemeCommande.getCommande(index);

        String state = request.queryParamOrDefault("state", "");
        if (state.equals("ENCOURS")) order.setState(Order.State.ENCOURS);
        if (state.equals("NOUVEAU")) order.setState(Order.State.NOUVEAU);
        if (state.equals("TERMINEE")) order.setState(Order.State.TERMINEE);
        if (state.equals("ANNULEE")) order.setState(Order.State.ANNULEE);

        Map<String, Object> model = new HashMap<>();

        model.put("orderID", id);
        model.put("order", order);
        return Template.render("order.html", model);
    }

}

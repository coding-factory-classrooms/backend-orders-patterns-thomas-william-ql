package org.example.controllers;

import org.example.core.Template;
import org.example.models.Order;
import org.example.models.Food;
import org.example.models.SystemeCommande;
import spark.Request;
import spark.Response;

import java.util.*;

public class SystemeCommandeController {

    private final SystemeCommande systemeCommande;

    public SystemeCommandeController(SystemeCommande systemeCommande){
        this.systemeCommande = systemeCommande;
    }


    public String list(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        model.put("commands", systemeCommande.getOrderList());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }


    public String commandHistory(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        int id = Integer.parseInt(request.params(":id"));
        int index = id - 1;


        Order order = systemeCommande.getCommande(index);

        String state = request.queryParamOrDefault("action", "");
        if (state.equals("undo"))  order.restore(order.getCommandHistory().undo());
        if (state.equals("redo"))  order.restore(order.getCommandHistory().redo());


        model.put("commands", systemeCommande.getOrderList());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }

    public String createCommande(Request request, Response response) {

        Food burger = new Food(Food.Type.BURGER, "Double Cheese");
        Food accompagnement = new Food(Food.Type.ACCOMPAGNEMENT, "Potatoes sauce creamy deluxe");
        Food boisson = new Food(Food.Type.BOISSON, "Coca Cola");


        Integer idMenu = Integer.parseInt(request.params(":id"));
        List<Food> menu  = new ArrayList<>();

        if (idMenu.equals(1)) menu = Arrays.asList(burger, accompagnement, boisson);
        if (idMenu.equals(2)) menu = Arrays.asList(burger, accompagnement);
        if (idMenu.equals(3)) menu = Arrays.asList(burger);

        Order order = new Order();
        order.addPlats(menu);
        order.setOnCommandeChangeListener(systemeCommande);
        systemeCommande.addOrder(order);

        Map<String, Object> model = new HashMap<>();

        model.put("commands", systemeCommande.getOrderList());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }


    public String systemHistory(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();


        String state = request.queryParamOrDefault("action", "");
        if (state.equals("undo"))  systemeCommande.restore(systemeCommande.getSystemeCommandeHistory().undo());
        if (state.equals("redo"))  systemeCommande.restore(systemeCommande.getSystemeCommandeHistory().redo());


        model.put("commands", systemeCommande.getOrderList());
        model.put("history", systemeCommande.getHistory());
        return Template.render("dashboard.html", model);
    }


}

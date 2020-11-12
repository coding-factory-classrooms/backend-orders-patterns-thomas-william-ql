package org.example.models;

import java.time.LocalDate;
import java.util.List;

public class OrderSave {

    private final CommandHistory commandHistory;

    private final int id;
    private final Commande.OnCommandeChangeListener onCommandeChangeListener;
    private final Commande.State state;
    private final  List<Food> plats;
    private final LocalDate localdate;

    public OrderSave(CommandHistory commandHistory, int id, Commande.OnCommandeChangeListener onCommandeChangeListener, Commande.State state, List<Food> plats, LocalDate localdate) {
        this.commandHistory = commandHistory;
        this.id = id;
        this.onCommandeChangeListener = onCommandeChangeListener;
        this.state = state;
        this.plats = plats;
        this.localdate = localdate;
    }

    public Commande getOrderFromOrderSave(){
        return new Commande(commandHistory, id, onCommandeChangeListener, state, plats, localdate);
    }
}

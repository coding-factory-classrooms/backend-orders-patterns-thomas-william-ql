package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class SystemeCommande implements Commande.OnCommandeChangeListener {
    private final List<Commande> orderList = new ArrayList<>();

    private final List<String> logs = new ArrayList<>();

    private final List<String> history = new ArrayList<>();

    public List<Commande> getOrderList(){
        return orderList;
    }

    public void addOrder(Commande commande){
        if (commande.getState() != Commande.State.NOUVEAU){
            return;
        }
        logs.add("Ajout des plats  "+ commande.getPlats() + ". date de commande :" + commande.getLocaldate());
        this.orderList.add(commande);
    }

    public List<String> getLogs() {
        return logs;
    }
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void onCommandeChange(Commande commande) {
        history.add("Commande etat : " + commande.getState() + " | date : " + commande.getLocaldate() + " | plats " + commande.getPlats());
    }
}

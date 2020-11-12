package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class SystemeCommande implements Commande.OnCommandeChangeListener {
    private final List<Commande> orderList = new ArrayList<>();
    private final List<String> history = new ArrayList<>();

    public List<Commande> getOrderList(){
        return orderList;
    }

    public Commande getCommande(int index){
        return this.orderList.get(index);
    }


    public void addOrder(Commande commande){
        if (commande.getState() != Commande.State.NOUVEAU){
            return;
        }
        this.orderList.add(commande);
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public void onCommandeChange(Commande commande) {
        System.out.println("Commande id : "+ commande.getId() +" | Modification de l'état en : "+ commande.getState());
        history.add("Order ID : " + commande.getId() + " | Commande etat : " + commande.getState() + " | date : " + commande.getLocaldate() + " | plats " + commande.getPlats());
    }
}

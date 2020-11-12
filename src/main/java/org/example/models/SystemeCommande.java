package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemeCommande implements Commande.OnCommandeChangeListener {



    private final SystemeCommandeHistory systemeCommandeHistory = new SystemeCommandeHistory();
    private List<Commande> orderList = new ArrayList<>();
    private final List<String> history = new ArrayList<>();
    private final LogSystem logSystem;

    public SystemeCommande(LogSystem logSystem) {
        this.logSystem = logSystem;
    }

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
        systemeCommandeHistory.save(this.orderList);

        System.out.println(systemeCommandeHistory.getCurrentlistOrderSave());
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public void onCommandeChange(Commande commande) {
        logSystem.addLog("Commande id : "+ commande.getId() +" | Modification de l'état en : "+ commande.getState());
        history.add("Order ID : " + commande.getId() + " | Commande etat : " + commande.getState() + " | date : " + commande.getLocaldate() + " | plats " + commande.getPlats());

        systemeCommandeHistory.save(this.orderList);
    }

    public SystemeCommandeHistory getSystemeCommandeHistory() {
        return systemeCommandeHistory;
    }

    public void restore(List<OrderSave> saveList) {
        System.out.println("RESTORE");
        if (saveList != null) this.orderList = saveList.stream().map(orderSave -> orderSave.getOrderFromOrderSave()).collect(Collectors.toList());
    }
}

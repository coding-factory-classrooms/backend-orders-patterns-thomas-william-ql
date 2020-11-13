package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemeCommande implements Order.OnCommandeChangeListener {



    private final SystemeCommandeHistory systemeCommandeHistory = new SystemeCommandeHistory();
    private List<Order> orderList = new ArrayList<>();
    private final List<String> history = new ArrayList<>();
    private final LogSystem logSystem;

    public SystemeCommande(LogSystem logSystem) {
        this.logSystem = logSystem;
    }

    public List<Order> getOrderList(){
        return orderList;
    }

    public Order getCommande(int index){
        return this.orderList.get(index);
    }


    public void addOrder(Order order){
        if (order.getState() != Order.State.NOUVEAU){
            return;
        }
        this.orderList.add(order);
        systemeCommandeHistory.save(this.orderList);

        System.out.println(systemeCommandeHistory.getCurrentlistOrderSave());
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public void onOrderChange(Order order) {
        logSystem.addLog("Commande id : "+ order.getId() +" | Modification de l'état en : "+ order.getState());
        history.add("Order ID : " + order.getId() + " | Commande etat : " + order.getState() + " | date : " + order.getLocaldate() + " | plats " + order.getPlats());

        systemeCommandeHistory.save(this.orderList);
    }

    public SystemeCommandeHistory getSystemeCommandeHistory() {
        return systemeCommandeHistory;
    }

    public void restore(List<OrderSave> saveList) {
        if (saveList != null) this.orderList = saveList.stream().map(OrderSave::getOrderFromOrderSave).collect(Collectors.toList());
    }
}

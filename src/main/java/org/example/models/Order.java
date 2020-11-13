package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public Order(OrderHistory orderHistory, int id, OnCommandeChangeListener onCommandeChangeListener, State state, List<Food> plats, LocalDate localdate) {
        this.orderHistory = orderHistory;
        this.id = id;
        this.onCommandeChangeListener = onCommandeChangeListener;
        this.state = state;
        this.plats = plats;
        this.localdate = localdate;
    }
    public Order(){

    }

    public interface OnCommandeChangeListener {
        void onOrderChange(Order order);
    }

    public enum State{
        NOUVEAU,
        ENCOURS,
        TERMINEE,
        ANNULEE,
    }



    private OrderHistory orderHistory = new OrderHistory();


    private int id = (int) Math.round((Math.random() * (9999 - 1)) + 1);
    private OnCommandeChangeListener onCommandeChangeListener;
    private State state = State.NOUVEAU;
    private List<Food> plats = new ArrayList<>();
    private LocalDate localdate = LocalDate.now();

    public State getState(){ return state; }
    public List<Food> getPlats(){ return plats; }
    public LocalDate getLocaldate(){ return localdate; }

    public void setState(State state){
        //Save this first state
        if (orderHistory.getCurrentCommandState() == null) this.orderHistory.addCommandState(save(this.state));

        this.state = state;
        this.orderHistory.addCommandState(save(state));
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onOrderChange(this);
        }
    }
    public void addPlats(List<Food> plats){
        this.plats.addAll(plats);
    }
    public void addPlats(Food food){
        this.plats.add(food);
    }
    public void setLocaldate(LocalDate localdate){
        this.localdate = localdate;
    }

    public void setOnCommandeChangeListener(OnCommandeChangeListener onCommandeChangeListener) {
        this.onCommandeChangeListener = onCommandeChangeListener;
    }

    public OrderHistory getCommandHistory() {
        return orderHistory;
    }

    public int getId() {
        return id;
    }


    public OrderState save(State state){
        return new OrderState(state);
    }

    public void restore(OrderState orderState) {
        System.out.println("RESTORE");
        if (orderState != null) this.state = orderState.getState();
    }

    public OrderSave getSave() {
       return new OrderSave(orderHistory, id,onCommandeChangeListener, state, plats, localdate);
    }

}

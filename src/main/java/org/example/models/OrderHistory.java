package org.example.models;

import java.util.ArrayList;

public class OrderHistory {

    private ArrayList<OrderState> history;
    private int currentState = -1;

    public OrderHistory() {
        this.history = new ArrayList<OrderState>();
    }

    public void addCommandState(OrderState orderState) {
        this.history.add(orderState);
        currentState = this.history.size() - 1;
    }

    public OrderState getCommandState(Integer index) {
        if (index >= 0  && this.history.size() > index) {
            return this.history.get(index);
        }
        return null;
    }

    public OrderState getCurrentCommandState() {
        return this.getCommandState(this.currentState);
    }

    public OrderState undo(){

        System.out.println("UNDO");
        if (currentState < 1) {
            OrderState orderState = getCommandState(0);
            return orderState;
        }

        currentState--;
        return getCommandState(currentState);
    }


    public OrderState redo(){
        System.out.println("REDO");
        if (currentState >= this.history.size() -1) {
            currentState = this.history.size() -1;
            return getCommandState(currentState);
        }

        currentState++;
        return getCommandState(currentState);
    }
}

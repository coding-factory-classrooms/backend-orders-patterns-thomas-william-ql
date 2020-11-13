package org.example.models;

public class OrderState {

    private Order.State state;

    public OrderState(Order.State state) {
        this.state = state;
    }

    public void setState(Order.State state) {
        this.state = state;
    }

    public Order.State getState() {
        return state;
    }
}

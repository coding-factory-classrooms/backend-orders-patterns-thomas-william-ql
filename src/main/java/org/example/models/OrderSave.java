package org.example.models;

import java.time.LocalDate;
import java.util.List;

public class OrderSave {

    private final OrderHistory orderHistory;

    private final int id;
    private final Order.OnCommandeChangeListener onCommandeChangeListener;
    private final Order.State state;
    private final  List<Food> plats;
    private final LocalDate localdate;

    public OrderSave(OrderHistory orderHistory, int id, Order.OnCommandeChangeListener onCommandeChangeListener, Order.State state, List<Food> plats, LocalDate localdate) {
        this.orderHistory = orderHistory;
        this.id = id;
        this.onCommandeChangeListener = onCommandeChangeListener;
        this.state = state;
        this.plats = plats;
        this.localdate = localdate;
    }

    public Order getOrderFromOrderSave(){
        return new Order(orderHistory, id, onCommandeChangeListener, state, plats, localdate);
    }
}

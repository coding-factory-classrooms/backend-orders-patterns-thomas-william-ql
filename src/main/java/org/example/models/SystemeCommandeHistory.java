package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemeCommandeHistory {

    private final List<List<OrderSave>> orderSaveList = new ArrayList<>();
    private int current = -1;

    public void save(List<Commande> commandeList) {
       this.orderSaveList.add(commandeList.stream().map(Commande::getSave).collect(Collectors.toList()));
        current = this.orderSaveList.size() - 1;
    }


    public List<OrderSave> getListOrderSave(Integer index) {
        if (index >= 0  && this.orderSaveList.size() > index) {
            return this.orderSaveList.get(index);
        }
        return null;
    }

    public List<OrderSave> getCurrentlistOrderSave() {
        return this.getListOrderSave(this.current);
    }

    public List<OrderSave> undo(){

        System.out.println("UNDO List<OrderSave>");
        if (current < 1) {
            List<OrderSave> listOrderSave = getListOrderSave(0);
            return listOrderSave;
        }

        current--;
        return getListOrderSave(current);
    }


    public List<OrderSave> redo(){
        System.out.println("REDO List<OrderSave>");
        if (current >= this.orderSaveList.size() -1) {
            current = this.orderSaveList.size() -1;
            return getListOrderSave(current);
        }

        current++;
        return getListOrderSave(current);
    }

    public boolean empty(){
        return this.orderSaveList.size() > 0;
    }
}

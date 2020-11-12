package org.example.models;

public class CommandState {

    private Commande.State state;

    public CommandState(Commande.State state) {
        this.state = state;
    }

    public void setState(Commande.State state) {
        this.state = state;
    }

    public Commande.State getState() {
        return state;
    }
}

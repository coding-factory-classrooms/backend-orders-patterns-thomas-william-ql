package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Commande {

    public interface OnCommandeChangeListener {
        void onCommandeChange(Commande commande);
    }

    public enum State{
        NOUVEAU,
        ENCOURS,
        TERMINEE,
        ANNULEE,
    }



    private final CommandHistory commandHistory = new CommandHistory();


    private final int id = (int) Math.round((Math.random() * (9999 - 1)) + 1);
    private OnCommandeChangeListener onCommandeChangeListener;
    private State state = State.NOUVEAU;
    private final List<Plats> plats = new ArrayList<>();
    private LocalDate localdate = LocalDate.now();

    public State getState(){ return state; }
    public List<Plats> getPlats(){ return plats; }
    public LocalDate getLocaldate(){ return localdate; }

    public void setState(State state){
        //Save this first state
        if (commandHistory.getCurrentCommandState() == null) this.commandHistory.addCommandState(save(this.state));

        this.state = state;
        this.commandHistory.addCommandState(save(state));
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onCommandeChange(this);
        }
    }
    public void addPlats(List<Plats> plats){
        this.plats.addAll(plats);
    }
    public void addPlats(Plats plats){
        this.plats.add(plats);
    }
    public void setLocaldate(LocalDate localdate){
        this.localdate = localdate;
    }

    public void setOnCommandeChangeListener(OnCommandeChangeListener onCommandeChangeListener) {
        this.onCommandeChangeListener = onCommandeChangeListener;
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public int getId() {
        return id;
    }


    public CommandState save(State state){
        return new CommandState(state);
    }

    public void restore(CommandState commandState) {
        System.out.println("RESTORE");
        if (commandState != null) this.state = commandState.getState();
    }

}

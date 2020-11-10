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



    private OnCommandeChangeListener onCommandeChangeListener;
    private State state = State.NOUVEAU;
    private List<Plats> plats = new ArrayList<>();
    private LocalDate localdate = LocalDate.now();

    public State getState(){ return state; }
    public List<Plats> getPlats(){ return plats; }
    public LocalDate getLocaldate(){ return localdate; }

    public void setState(State state){
        this.state = state;
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onCommandeChange(this);
        }
    }
    public void addPlats(List<Plats> plats){
        this.plats.addAll(plats);
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onCommandeChange(this);
        }
    }    public void addPlats(Plats plats){
        this.plats.add(plats);
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onCommandeChange(this);
        }
    }
    public void setLocaldate(LocalDate localdate){
        this.localdate = localdate;
        if (onCommandeChangeListener != null) {
            onCommandeChangeListener.onCommandeChange(this);
        }
    }

    public void setOnCommandeChangeListener(OnCommandeChangeListener onCommandeChangeListener) {
        this.onCommandeChangeListener = onCommandeChangeListener;
    }
}

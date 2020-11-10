package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Commande {

    public enum State{
        NOUVEAU,
        ENCOURS,
        TERMINEE,
        ANNULEE,
    }

    private State state = State.NOUVEAU;
    private List<Plats> plats = new ArrayList<>();
    //private LocalDate localdate = LocalDate.now();

    public State getState(){ return state; }
    public List<Plats> getPlats(){ return plats; }
   // public LocalDate getLocaldate(){ return localdate; }

    public void setState(State state){ this.state = state; }
    public void setPlats(List<Plats> plats){ this.plats = plats; }
    //public void setLocaldate(LocalDate localdate){ this.localdate = localdate; }
}

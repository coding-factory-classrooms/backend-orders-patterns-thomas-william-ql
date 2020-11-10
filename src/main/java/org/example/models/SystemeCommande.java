package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class SystemeCommande {
    private List<Commande> commande = new ArrayList<>();

    public List<Commande> getCommande(){
        return commande;
    }

    public void AjoutCommande(Commande commande){
        if (commande.getState() != Commande.State.NOUVEAU){
            return;
        }
        this.commande.add(commande);
    }
}

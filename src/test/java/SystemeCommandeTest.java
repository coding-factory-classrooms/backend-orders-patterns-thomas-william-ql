import org.example.models.Burger;
import org.example.models.Commande;
import org.example.models.Plats;
import org.example.models.SystemeCommande;
import org.junit.Assert;
import org.junit.Test;

public class SystemeCommandeTest {


    @Test
    public void listOrderSizeIncreaseAfterAdd(){
        SystemeCommande systemeCommande = new SystemeCommande();

        Assert.assertEquals(0 , systemeCommande.getOrderList().size());

        systemeCommande.addOrder(new Commande());

        Assert.assertEquals(1 , systemeCommande.getOrderList().size());
    }


    @Test
    public void logGetNewEntryAfterAddingCommand(){
        SystemeCommande systemeCommande = new SystemeCommande();

        Assert.assertEquals(0 , systemeCommande.getLogs().size());

        systemeCommande.addOrder(new Commande());

        Assert.assertEquals(1 , systemeCommande.getLogs().size());
    }
    @Test
    public void commandIsNotAddedIfNotNouveauState(){
        SystemeCommande systemeCommande = new SystemeCommande();

        Assert.assertEquals(0 , systemeCommande.getLogs().size());

        Commande commande = new Commande();
        commande.setState(Commande.State.TERMINEE);
        systemeCommande.addOrder(commande);


        Assert.assertEquals(0 , systemeCommande.getLogs().size());
    }



    @Test
    public void modifCommandAddedToHistory(){
        SystemeCommande systemeCommande = new SystemeCommande();

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        Commande commande = new Commande();
        systemeCommande.addOrder(commande);

        commande.setOnCommandeChangeListener(systemeCommande);
        commande.addPlats(new Plats());

        System.out.println(systemeCommande.getHistory());
        Assert.assertEquals(1 , systemeCommande.getHistory().size());

    }


}

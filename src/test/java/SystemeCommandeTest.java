import org.example.models.Commande;
import org.example.models.LogSystem;
import org.example.models.SystemeCommande;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SystemeCommandeTest {

    LogSystem logSystem;
    SystemeCommande systemeCommande;
    @Before
    public void before(){
         logSystem = new LogSystem();
         systemeCommande = new SystemeCommande(new LogSystem());
    }

    @Test
    public void listOrderSizeIncreaseAfterAdd(){


        Assert.assertEquals(0 , systemeCommande.getOrderList().size());

        systemeCommande.addOrder(new Commande());

        Assert.assertEquals(1 , systemeCommande.getOrderList().size());
    }


    @Test
    public void logGetNewEntryAfterAddingCommand(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        systemeCommande.addOrder(new Commande());

        Assert.assertEquals(1 , systemeCommande.getHistory().size());
    }
    @Test
    public void commandIsNotAddedIfNotNouveauState(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        Commande commande = new Commande();
        commande.setState(Commande.State.TERMINEE);
        systemeCommande.addOrder(commande);


        Assert.assertEquals(0 , systemeCommande.getHistory().size());
    }



    @Test
    public void modifCommandAddedToHistory(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        Commande commande = new Commande();
        systemeCommande.addOrder(commande);

        commande.setOnCommandeChangeListener(systemeCommande);
        commande.setState(Commande.State.ENCOURS);

        System.out.println(systemeCommande.getHistory());
        Assert.assertEquals(1 , systemeCommande.getHistory().size());

    }


}

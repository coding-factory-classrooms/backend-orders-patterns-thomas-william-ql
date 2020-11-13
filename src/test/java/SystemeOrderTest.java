import org.example.models.Order;
import org.example.models.LogSystem;
import org.example.models.SystemeCommande;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SystemeOrderTest {

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

        systemeCommande.addOrder(new Order());

        Assert.assertEquals(1 , systemeCommande.getOrderList().size());
    }


    @Test
    public void logGetNewEntryAfterAddingCommand(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        systemeCommande.addOrder(new Order());

        Assert.assertEquals(1 , systemeCommande.getHistory().size());
    }
    @Test
    public void commandIsNotAddedIfNotNouveauState(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        Order order = new Order();
        order.setState(Order.State.TERMINEE);
        systemeCommande.addOrder(order);


        Assert.assertEquals(0 , systemeCommande.getHistory().size());
    }



    @Test
    public void modifCommandAddedToHistory(){

        Assert.assertEquals(0 , systemeCommande.getHistory().size());

        Order order = new Order();
        systemeCommande.addOrder(order);

        order.setOnCommandeChangeListener(systemeCommande);
        order.setState(Order.State.ENCOURS);

        System.out.println(systemeCommande.getHistory());
        Assert.assertEquals(1 , systemeCommande.getHistory().size());

    }


}

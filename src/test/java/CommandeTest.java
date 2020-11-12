import org.example.models.Commande;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandeTest {

    private Commande commande;

    @Before
    public void before(){
        commande = new Commande();
    }

    @Test
    public void updateCommandStateUpdateHistory(){

        commande.setState(Commande.State.ENCOURS);
        commande.getState();
        Assert.assertEquals(
                Commande.State.ENCOURS,
                commande.getCommandHistory().getCurrentCommandState().getState());
    }

    @Test
    public void verifyEmptyUndo(){
       Assert.assertNull(commande.getCommandHistory().undo());
    }


    @Test
    public void updateCommandStateThenUndo(){

        commande.setState(Commande.State.ENCOURS);

        commande.restore(commande.getCommandHistory().undo());

        Assert.assertEquals(Commande.State.NOUVEAU, commande.getState());
    }


    @Test
    public void sequenceUndoRedo(){

        commande.setState(Commande.State.ENCOURS);

        commande.restore(commande.getCommandHistory().undo());

        commande.restore(commande.getCommandHistory().redo());

        Assert.assertEquals(Commande.State.ENCOURS, commande.getState());
    }



}

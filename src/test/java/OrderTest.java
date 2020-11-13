import org.example.models.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Order order;

    @Before
    public void before(){
        order = new Order();
    }

    @Test
    public void updateCommandStateUpdateHistory(){

        order.setState(Order.State.ENCOURS);
        order.getState();
        Assert.assertEquals(
                Order.State.ENCOURS,
                order.getCommandHistory().getCurrentCommandState().getState());
    }

    @Test
    public void verifyEmptyUndo(){
       Assert.assertNull(order.getCommandHistory().undo());
    }


    @Test
    public void updateCommandStateThenUndo(){

        order.setState(Order.State.ENCOURS);

        order.restore(order.getCommandHistory().undo());

        Assert.assertEquals(Order.State.NOUVEAU, order.getState());
    }


    @Test
    public void sequenceUndoRedo(){

        order.setState(Order.State.ENCOURS);

        order.restore(order.getCommandHistory().undo());

        order.restore(order.getCommandHistory().redo());

        Assert.assertEquals(Order.State.ENCOURS, order.getState());
    }



}

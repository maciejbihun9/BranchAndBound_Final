import model.Limits;
import controller.Node;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MaciekBihun on 2016-05-03.
 */
public class LimitsTest {


    Node node;
    double [] startingVariables = {3, 12, 5.1, 2.1, 2.2, 3.1};
    int [] functionParameters = {2, 5, 1, 3, 7, 6};
    @Before
    public void initialize(){

        node= new Node(startingVariables);

        Limits.getInstance().addConstraintToVariable(3);
        Limits.getInstance().addConstraintToVariable(4);
    }

   /* @Test
    public void constraintVariablesTest(){
        List<Integer> lista = Parameters.getInstance().getFunctionParameters();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.removeAll(Limits.getVariablesWithConstraints());
        System.out.println(lista);
    }*/

    @Test
    public void getNotIntegerVariableIndexes(){
        System.out.println(node.getNotIntegerVariableIndexes());
    }
}

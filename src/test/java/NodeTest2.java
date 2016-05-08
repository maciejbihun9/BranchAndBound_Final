import implementations.Limit;
import implementations.Limits;
import implementations.Node;
import implementations.Parameters;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;


/**
 * Created by MaciekBihun on 2016-05-07.
 */
public class NodeTest2 {

    Node node;
    double [] startingVariables = {4, 12.2, 5.1, 2.5, 2, 1};
    List<Integer> functionParameters = new ArrayList<>(Arrays.asList(5,3,4,1,1,12));

    @Before
    public void initialize(){

        Parameters.setFunctionParameters(functionParameters);
        node = new Node(startingVariables);

        //Create couple limits
        Integer [] limitParams1 = {2, 0, 2, 1 ,0, 1};
        Limit limit1 = new Limit(limitParams1, ">", 100);

        Integer [] limitParams2 = {1, 5 ,3,2, 1, 0};
        Limit limit2 = new Limit(limitParams2, "<=", 150);

        Limits.addLimit(limit1);
        Limits.addLimit(limit2);

        //Limits.addConstraintToVariable(4);
    }

    //Działa ok
    @Test
    public void getNotConstrainedIndexes(){
        //node.setConstraintOnVariableWithMinParam();
        System.out.println("Not constrained indexes : " + node.getNotConstrainedIndexes());
    }

    @Test
    public void getMinParamIndexFromLimit(){
        //Limits.addConstraintToVariable(2);
        //Limits.addConstraintToVariable(3);
        System.out.println("Not constrained indexes: " + node.getNotConstrainedIndexes());
        List<Limit> limitsList = Limits.getInstance().getLimitsList();
        for (int i = 0 ;i < Limits.getInstance().getLimitsList().size(); i++) {
            if(!limitsList.get(i).checkIfFulfilEquation(startingVariables)){
                System.out.println("Który nie spełnia równania: " + i);
                System.out.println("Index with min. param: " + node.getIndexFromNotConstrainedVariablesWithMinParam(i));
                assertEquals(node.getIndexFromNotConstrainedVariablesWithMinParam(i), 3, 0);
            }
        }

    }

    //Ctrl + j - genarate for loop
    @Test
    public void checkLimitsTest(){
        for (int i = 0; i < node.getNodeVariables().length; i++) {
            System.out.println("Zmienne przed zmianami : " + node.getNodeVariables()[i]);
        }
        node.checkLimits2();

        for (int i = 0; i < node.getNodeVariables().length; i++) {
            System.out.println("Zmienne po zmianami : " + node.getNodeVariables()[i]);
        }
    }


}

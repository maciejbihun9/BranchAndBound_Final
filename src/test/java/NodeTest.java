import implementations.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MaciekBihun on 2016-05-03.
 */
public class NodeTest {

    Node node;
    double [] startingVariables = {4, 12.2, 5.1, 2.5, 2, 1};
    List<Integer> functionParameters = new ArrayList<>(Arrays.asList(5,1,4,3,2,12));

    @Before
    public void initialize(){

        Parameters.setFunctionParameters(functionParameters);

        int [] limitParams1 = {2, 3, 0, 0 ,4, 0};
        Limit limit1 = new Limit(limitParams1, ">", 24);

        int [] limitParams2 = {4, 1 ,3,0, 5, 2};
        Limit limit2 = new Limit(limitParams2, ">=", 150);

        int [] limitParams3 = {2,2, 0, 3 ,4, 3};
        Limit limit3 = new Limit(limitParams3, ">=", 25);

        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);
        Limits.addLimit(limit3);


        node = new Node(startingVariables);
    }

    @Test
    public void NodeVariablesTest(){
        for(int i = 0; i < node.getVariablesForHigherNode().length; i++) {
            System.out.print("Zmienne dla higher Node : " + node.getVariablesForHigherNode()[i]);
        }
            System.out.println();
            for(int i = 0; i < node.getVariablesForLowerNode().length; i++){
                System.out.print("Zmienne dla lower Node : " + node.getVariablesForLowerNode()[i]);

        }

    }

    @Test
    public void paramWithMinValueIndex(){
        if(node.getNotIntegerVariableIndexes().isEmpty())
            assertEquals(node.getIndexOfVariableWithMinParam(), -1, 0);
        else
            assertEquals(node.getIndexOfVariableWithMinParam(), 2, 0);
        System.out.println("Indeks parametru z minimalą wartością:" + node.getIndexOfVariableWithMinParam());
    }

    @Test
    public void setLimitOnVariableWithMinParam(){
        System.out.println("Zmienne przed nadaniem ograniczenia: " + node.getNotIntegerVariableIndexes());
        node.setConstraintOnVariableWithMinParam();
        System.out.println("Zmienne po nadaniu ograniczenia: " + node.getNotIntegerVariableIndexes());
    }



    @Test
    public void getNotIntegerVariablesTest(){
            System.out.println("Not integer variables : " + node.getNotIntegerVariableIndexes());

    }

    @Test
    public void getVariablesForHeigherNode(){
        for (int i = 0; i < node.getVariablesForHigherNode().length; i++){
            System.out.println(node.getVariablesForHigherNode()[i]);
        }

    }


    @Test
    public void getIndexOfVariableWithMinParam(){
        System.out.println("Indeks with min param: " + node.getIndexOfVariableWithMinParam());
        if(node.getNotIntegerVariableIndexes().isEmpty())
            assertEquals(node.getIndexOfVariableWithMinParam(), -1);
        else
            assertEquals(node.getIndexOfVariableWithMinParam(), 1);
    }

    @Test
    public void getConstrainedVariables(){
        System.out.println("Limits:" + Limits.getVariablesWithConstraints());
    }

    @Test
    public void testbranch(){
        Node node2 = new Node(node.getVariablesForHigherNode());
        for(int i = 0 ; i < node.getVariablesForHigherNode().length;i++) {
            System.out.println("Nowe parametry : " + node2.getVariablesForHigherNode()[i]);
        }

    }

    @Test
    public void getNodeSumTest(){
        System.out.println("Przed podziałe : " + node.getNodeSum());
        Node node1 = new Node(node.getVariablesForHigherNode());
        System.out.println("Po podziałe : " + node1.getNodeSum());

    }

}

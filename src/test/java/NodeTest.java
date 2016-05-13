import controller.Limit;
import controller.Node;
import model.Limits;
import model.Parameters;
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
    List<Integer> functionParameters = new ArrayList<>(Arrays.asList(5,3,4,1,2,12));

    @Before
    public void initialize(){

        Parameters.setFunctionParameters(functionParameters);

        Integer [] limitParams1 = {2, 3, 0, 0 ,4, 0};
        Limit limit1 = new Limit(limitParams1, ">", 24);

        Integer [] limitParams2 = {4, 1 ,3,0, 5, 2};
        Limit limit2 = new Limit(limitParams2, "<=", 40);

        Integer [] limitParams3 = {3,2, 0, 3 ,4, 3};
        Limit limit3 = new Limit(limitParams3, ">=", 25);

        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);
        Limits.addLimit(limit3);

        node = new Node(startingVariables);
    }

    //Works fine for all cases.
    @Test
    public void getNotIntegerIndexes(){
        System.out.println("Not integer indexes:" + node.getNotIntegerVariableIndexes());
    }


    //Works fine for all cases.
    @Test
    public void indexOfVariableWithMinParam(){
        System.out.println("Index of variable with the min param: " + node.getIndexOfVariableWithMinParam());
        assertEquals(node.getIndexOfVariableWithMinParam(), 3);
    }

    //Works fine for all cases.
    @Test
    public void setConstraintOnVariableWithMinParam(){
        System.out.println("Indexes before constraint: " + node.getNotIntegerVariableIndexes());
        Limits.addConstraintToVariable(1);
        System.out.println("Indexes after constraint: " + node.getNotIntegerVariableIndexes());
        System.out.println("List of all constraints: " + Limits.getVariablesWithConstraints());
    }

    //Gets all indexes without constraints. Gets also integer variables.
    @Test
    public void getNotConstrainedIndexes(){
        System.out.println("Pobierz indeksy bez constraints: " + node.getNotConstrainedIndexes());
    }

    //Works well for all
    @Test
    public void getIndexeWithoutConstraintWithMinParam(){
        Limits.addConstraintToVariable(0);
        Limits.addConstraintToVariable(1);
        System.out.println("The lowest index:" + node.getIndexFromNotConstrainedVariablesWithMinParam(2));
    }

    @Test
    public void checkLimitsTest(){
        /*Limits.addConstraintToVariable(5);
        Limits.addConstraintToVariable(4);
        Limits.addConstraintToVariable(2);
        Limits.addConstraintToVariable(1);
        Limits.addConstraintToVariable(0);*/
        System.out.println("Limits list: " + Limits.getVariablesWithConstraints());
        System.out.println("Check all limits test: ");
        System.out.println(node.getIndexFromNotConstrainedVariablesWithMinParam(1));
    }

    @Test
    public void checkLimitations(){
        System.out.println("Before checking limits");
        //Prints all array elements before checking limits.
        for (int i = 0; i < node.getNodeVariables().length; i++) {
            System.out.println(node.getNodeVariables()[i]);
        }
        node.checkLimits2();
        System.out.println("After checking limits");
        for (int i = 0; i < node.getNodeVariables().length; i++) {
            System.out.println(node.getNodeVariables()[i]);
        }
    }

    @Test
    public void branching(){
        Node node1 = new Node(node.getVariablesForHigherNode());
        for (int i = 0; i < node1.getNodeVariables().length; i++) {
            System.out.println(node1.getNodeVariables()[i]);
        }
        Limits.addConstraintToVariable(node1.getIndexOfVariableWithMinParam());
        Limits.addConstraintToVariable(2);
        System.out.println("Zmienne z constrainami: " +Limits.getVariablesWithConstraints());
        Node node2 = new Node(node1.getVariablesForHigherNode());
        for (int i = 0; i < node2.getNodeVariables().length; i++) {
            System.out.println(node2.getNodeVariables()[i]);
        }
    }


}

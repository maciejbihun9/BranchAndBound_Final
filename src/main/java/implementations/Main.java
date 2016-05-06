package implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Main {

    private static int counter = 0;


    public static void main(String[] args) {

        double [] startingVariables = {4.1, 6.2, 5.9, 4.9, 3, 4.1, 2.14, 12.1};
        //Enter function parameters.
        List<Integer> functionParameters = new ArrayList<Integer>();
        functionParameters.add(2);
        functionParameters.add(5);
        functionParameters.add(3);
        functionParameters.add(1);
        functionParameters.add(4);
        functionParameters.add(3);
        functionParameters.add(2);
        functionParameters.add(1);



        //Set function parameters only once for entire application.
        Parameters.setFunctionParameters(functionParameters);

        //Enter starting variables.


        //Enter function limits.
        //Length of the array must be the same as functionParameters.
        int [] limitParams1 = {2, 3, 0, 0 ,4, 0};
        Limit limit1 = new Limit(limitParams1, ">", 25);

        int [] limitParams2 = {4, 1 ,3,0, 5, 2};
        Limit limit2 = new Limit(limitParams2, ">=", 15);

        int [] limitParams3 = {2,2, 0, 3 ,4, 3};
        Limit limit3 = new Limit(limitParams3, ">=", 25);

        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);
        Limits.addLimit(limit3);

        Node startNode = new Node(startingVariables);
        for (int i = 0;i < startNode.getVariablesForHigherNode().length; i++){
            System.out.print(startNode.getId() + " " + startNode.getVariablesForHigherNode()[i] + " ");
        }
        System.out.println();
        NodesSumRepository.getInstance().putNodeResult(startNode.getId(), startNode.getNodeSum());
        double [] higherVariables = startNode.getVariablesForHigherNode();
        double [] lowerVariables = startNode.getVariablesForLowerNode();

        Limits.addConstraintToVariable(startNode.getIndexOfVariableWithMinParam());

        branchNode(higherVariables, lowerVariables);

        System.out.println("Rezultaty z mapy : " + NodesSumRepository.getInstance().getNodeResults());

    }

    public static void branchNode(double [] higherNode, double [] lowerNode){

        /*counter++;
        if(counter > 7)
            return;*/

        Node node1 = new Node(higherNode);
        Node node2 = new Node(lowerNode);

        //If there is no more not integer variables then return from function
        if(node1.getIndexOfVariableWithMinParam() == -1 || node2.getIndexOfVariableWithMinParam() == -1)
            return;
        System.out.println("Wyniki dla noda 1");
        for (int i = 0;i < node1.getVariablesForHigherNode().length; i++){
            System.out.print(node1.getId() + " " + node1.getVariablesForHigherNode()[i] + " ");
        }

        System.out.println("Wyniki dla noda 2");
        for (int i = 0;i < node2.getVariablesForLowerNode().length; i++){
            System.out.print(node2.getId() + " " + node2.getVariablesForLowerNode()[i] + " ");
        }

        System.out.println();

        NodesSumRepository.getInstance().putNodeResult(node1.getId(), node1.getNodeSum());
        NodesSumRepository.getInstance().putNodeResult(node2.getId(), node2.getNodeSum());
        double [] higherVariables = node1.getVariablesForHigherNode();
        double [] lowerVariables = node1.getVariablesForLowerNode();

        double [] higherVariables2 = node2.getVariablesForHigherNode();
        double [] lowerVariables2 = node2.getVariablesForLowerNode();

        node1.setConstraintOnVariableWithMinParam();

        branchNode(higherVariables,lowerVariables);
        branchNode(higherVariables2,lowerVariables2);

    }

}

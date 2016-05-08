package implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Main {

    private static int counter = 0;
    private static Executor exec;


    public static void main(String[] args) {

        //double [] startingVariables = {4.1, 6.2, 5.9, 4.9, 3};
        double [] startingVariables = {8, 2.25};
        //Enter function parameters.
        List<Integer> functionParameters = new ArrayList<Integer>();
        functionParameters.add(3);
        functionParameters.add(5);

        //Set function parameters only once for entire application.
        Parameters.setFunctionParameters(functionParameters);

        //Enter starting variables.


        //Enter function limits.
        //Length of the array must be the same as functionParameters.
        /*int [] limitParams1 = {2, 3, 0, 0 ,4, 0};
        Limit limit1 = new Limit(limitParams1, ">", 25);

        int [] limitParams2 = {4, 1 ,3,0, 5, 2};
        Limit limit2 = new Limit(limitParams2, ">=", 15);

        int [] limitParams3 = {2,2, 0, 3 ,4, 3};
        Limit limit3 = new Limit(limitParams3, ">=", 25);*/

        Integer [] limitParams = {2, 4};
        Limit limit1 = new Limit(limitParams, "<=", 25);

        Integer [] limitParams2 = {1, 0};
        Limit limit2 = new Limit(limitParams2, "<=", 8);

        Integer [] limitParams3 = {2, 0};
        Limit limit3 = new Limit(limitParams3, "<=", 10);

        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);
        Limits.addLimit(limit3);

        Node startNode = new Node(startingVariables);
        for (int i = 0;i < startNode.getVariablesForHigherNode().length; i++){
            System.out.print(startNode.getId() + " " + startNode.getVariablesForHigherNode()[i] + " ");
        }

        System.out.println();

        for (int i = 0;i < startNode.getVariablesForLowerNode().length; i++){
            System.out.print(startNode.getId() + " " + startNode.getVariablesForLowerNode()[i] + " ");
        }

        System.out.println();

        //Put startingNode function sum to the repository.
        NodesSumRepository.getInstance().putNodeResult(startNode.getId(), startNode.getNodeSum());

        double [] higherVariables = startNode.getVariablesForHigherNode();
        double [] lowerVariables = startNode.getVariablesForLowerNode();

        Limits.addConstraintToVariable(startNode.getIndexOfVariableWithMinParam());

        branchNode(higherVariables, lowerVariables);

        System.out.println("Rezultaty z mapy : " + NodesSumRepository.getInstance().getNodeResults());

    }

    /**
     *
     * @param higherNode
     * @param lowerNode
     */
    public static void branchNode(double [] higherNode, double [] lowerNode){

        Node node2 = new Node(lowerNode);
        node2.checkLimits2();

        Node node1 = new Node(higherNode);
        node1.checkLimits2();

        if(node1.isThereAnyNotIntegerVariables() == false){
            System.out.println("Integer branching has ended");
            return;
        }


        //If there is no more not integer variables then return from function
        if(node1.getIndexOfVariableWithMinParam() == -1 || node2.getIndexOfVariableWithMinParam() == -1)
            return;

        //Add node sum to the repository
        NodesSumRepository.getInstance().putNodeResult(node1.getId(), node1.getNodeSum());
        NodesSumRepository.getInstance().putNodeResult(node2.getId(), node2.getNodeSum());

        //Get adjusted variables for node1.
        double [] higherVariables = node1.getVariablesForHigherNode();
        double [] lowerVariables = node1.getVariablesForLowerNode();

        //Get adjusted variables for node2.
        double [] higherVariables2 = node2.getVariablesForHigherNode();
        double [] lowerVariables2 = node2.getVariablesForLowerNode();

        //Set constraint on minimal value.
        node1.setConstraintOnVariableWithMinParam();
        node2.setConstraintOnVariableWithMinParam();

        Runnable createNodes = new Runnable() {
            @Override
            public void run() {
                //creates two new nodes.
                branchNode(higherVariables,lowerVariables);
                branchNode(higherVariables2,lowerVariables2);
            }
        };

        exec = Executors.newFixedThreadPool(4);
        exec.execute(createNodes);


    }


    /*
    Po storzeniu nowego noda sprawdź, czy zostały spełnione warunki zadania.
    Jeśli tak to nie rób nic.
    Jeśli nie to dopasuj zmienną, która nie była jeszcze podzielona, jest
    nie całkowita. Sprawdź równanie ponownie.
     */

}

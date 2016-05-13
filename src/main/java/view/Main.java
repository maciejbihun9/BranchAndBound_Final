package view;

import controller.Limit;
import controller.Node;
import model.Limits;
import model.NodesSumRepository;
import model.Parameters;

import java.util.ArrayList;
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
        double [] startingVariables = {3.3, 3.25, 4.16, 2.1};
        //Enter function parameters.
        List<Integer> functionParameters = new ArrayList<Integer>();
        functionParameters.add(2);
        functionParameters.add(4);
        functionParameters.add(3);
        functionParameters.add(1);

        //Set function parameters only once for entire application.
        Parameters.setFunctionParameters(functionParameters);

        Integer [] limitParams = {0, 2,0 , -4};
        Limit limit1 = new Limit(limitParams, "<=", 10);

        Integer [] limitParams2 = {2, 0, 3, 0};
        Limit limit2 = new Limit(limitParams2, "<=", 25);


        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);

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

        branchNode(lowerVariables, higherVariables);

        System.out.println("Starting node result : " + startNode.getNodeSum());

    }

    public static void branchNode(double [] lowerVariables, double [] higherVariables){

        System.out.println("Zmienne z constrainami: " + Limits.getVariablesWithConstraints());
        Node node1 = new Node(lowerVariables);
        Node node2 = new Node(higherVariables);

        for (int i = 0; i < node1.getNodeVariables().length; i++) {
            System.out.println("Node variable: "+node1.getId() + " " + node1.getNodeVariables()[i]);
        }

        for (int i = 0; i < node2.getNodeVariables().length; i++) {
            System.out.println("Node variable: "+node2.getId() + " " + node2.getNodeVariables()[i]);
        }

        System.out.println("Node result: " + node1.getNodeSum());
        System.out.println("Node result: " + node2.getNodeSum());

        NodesSumRepository.getInstance().putNodeResult(node1.getId(), node1.getNodeSum());
        NodesSumRepository.getInstance().putNodeResult(node2.getId(), node2.getNodeSum());


        branchNode(node1.getVariablesForLowerNode(), node1.getVariablesForHigherNode());
        branchNode(node2.getVariablesForLowerNode(), node2.getVariablesForHigherNode());

        Limits.addConstraintToVariable(node1.getIndexOfVariableWithMinParam());
        //Limits.addConstraintToVariable(node2.getIndexOfVariableWithMinParam());

    }



}

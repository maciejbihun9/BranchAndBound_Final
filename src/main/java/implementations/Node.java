package implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Node {

    static int id = 0;
    int counter = 0;

    private double [] nodeVariables;

    public Node(double [] nodeVariables){
        id++;
        counter = id;
        this.nodeVariables = nodeVariables;
    }

    /**
     *
     * @return Node id.
     */
    public int getId(){
        return counter;
    }

    /**
     *
     * @return Result of the equation with node variables.
     */
    public double getNodeSum(){
        double sum = 0;
        for(int i = 0 ; i < nodeVariables.length; i++){
            sum += nodeVariables[i] * Parameters.getInstance().getFunctionParameters().get(i);
        }
        return sum;
    }

    /**
     *
     * @return Node variables array.
     */
    public double [] getNodeVariables(){
        return nodeVariables;
    }

    /**
     *
     * @return variables array with new floored variable.
     */
    public double [] getVariablesForLowerNode(){
        double [] zmienne = nodeVariables.clone();
        for(int i = 0; i < zmienne.length; i++){
            if(i == getIndexOfVariableWithMinParam()){
                zmienne[i] = Math.floor(zmienne[i]);
            }
        }
        return zmienne;
    }

    /**
     *
     * @return variables array with new ceiled variable.
     */
    public double [] getVariablesForHigherNode(){
        double [] zmienne = nodeVariables.clone();
        for(int i = 0; i < zmienne.length; i++){
            if(i == getIndexOfVariableWithMinParam()){
                zmienne[i] = Math.ceil(zmienne[i]);
            }
        }
        return zmienne;
    }

    /**
     *
     * @return List with indexes with not integer variables.
     * Returns only not used indexes.
     */
    public List <Integer> getNotIntegerVariableIndexes(){
        List<Integer> notIntegerVariableList = new ArrayList<Integer>();
        for (int i = 0; i < nodeVariables.length; i++) {
            //jeśli liczba jest nie całkowita
            if (!((nodeVariables[i] == Math.floor(nodeVariables[i])) &&
                    !Double.isInfinite(nodeVariables[i]))) {
                //dodaj ją do tablicy liczb nie całkowitych.
                notIntegerVariableList.add(i);
            }

        }
        for (int j = 0 ; j < Limits.getVariablesWithConstraints().size(); j++){
            notIntegerVariableList.remove(Limits.getVariablesWithConstraints().get(j));
        }
        //System.out.println("Liczby nie całkowite:" + notIntegerVariableList);
        return notIntegerVariableList;
    }

    /**
     *
     * @return Index of Variable which is not integer,
     * has not been already branch and got minimum parameter value.
     */

    public int getIndexOfVariableWithMinParam(){
        if(getNotIntegerVariableIndexes().isEmpty())
            return -1;
        else if(getNotIntegerVariableIndexes().size() == 1)
            return getNotIntegerVariableIndexes().get(0);
        else{
            //Check which index has minimum value.
            List<Integer> notIntegerVariableIndexes = getNotIntegerVariableIndexes();
            List<Integer> functionParametersList = Parameters.getInstance().getFunctionParameters();
            //set the minimum value at first list element.

            int minValue = Parameters.getInstance().getFunctionParameters().get(notIntegerVariableIndexes.get(0));
            int minValueIndex = notIntegerVariableIndexes.get(0);
            for (int i = 1 ;i < notIntegerVariableIndexes.size(); i++){
                if(functionParametersList.get(notIntegerVariableIndexes.get(i)) < minValue) {
                    minValueIndex = notIntegerVariableIndexes.get(i);
                }
            }
            return minValueIndex;
        }

    }

    public void setConstraintOnVariableWithMinParam(){
        int minParamIndex = getIndexOfVariableWithMinParam();
        if(!Limits.getVariablesWithConstraints().contains(minParamIndex))
        Limits.addConstraintToVariable(minParamIndex);
    }

    /**
     *
     * @return List with not constrained variables indexes.
     */
    public List<Integer> getNotConstrainedIndexes(){
        List<Integer> constrainedIndexes = Limits.getVariablesWithConstraints();
        List<Integer> functionIndexes = new ArrayList<Integer>();
        for (int i = 0; i < nodeVariables.length; i++) {
            functionIndexes.add(i);
        }
        //List<Integer> notIntegerIndexes = getNotIntegerVariableIndexes();
        for(int i = 0 ; i < constrainedIndexes.size(); i++){
            functionIndexes.remove(constrainedIndexes.get(i));
        }
        return functionIndexes;
    }

    /**
     *
     * @param whichLimit
     * @return index with the lowest parameter value in specified limit.
     */
    public int getIndexFromNotConstrainedVariablesWithMinParam(int whichLimit){
        if(getNotConstrainedIndexes().isEmpty())
            return -1;
        else if(getNotConstrainedIndexes().size() == 1)
            return getNotConstrainedIndexes().get(0);
        else{
            //Check which index has minimum value.
            List<Integer> notConstrainedIndexes = getNotConstrainedIndexes();
            Limit limit = Limits.getInstance().getLimitsList().get(whichLimit);
            //set the minimum value at first list element.

            //set first element from not constrained indexes.
            for(int i = 0 ; i < limit.getLimitParameters().length; i++){
                if(limit.getLimitParameters()[i] == 0){
                    Integer indeks = i;
                    notConstrainedIndexes.remove(indeks);
                }
            }

            //If each limit parameter has value zero then return -1;
            if(notConstrainedIndexes.isEmpty())
                return -1;

            int minValue = limit.getLimitParameters()[notConstrainedIndexes.get(0)];
            int minValueIndex = notConstrainedIndexes.get(0);
            for (int i = 1 ;i < notConstrainedIndexes.size(); i++){
                if(limit.getLimitParameters()[notConstrainedIndexes.get(i)] < minValue &&
                        limit.getLimitParameters()[notConstrainedIndexes.get(i)] != 0) {
                    minValueIndex = notConstrainedIndexes.get(i);
                }
            }

            return minValueIndex;
        }

    }


    /**
     * If equation does not fulfil limits then adjust apprioprate variable
     * to match each limit equation.
     */
    public void checkLimits2(){
        //Get not integer variables

        List<Limit> limitsList = Limits.getInstance().getLimitsList();
        for (int i = 0 ; i < limitsList.size(); i++){
            //Jeśli nie spełnia równania
            if(!limitsList.get(i).checkIfFulfilEquation(nodeVariables)){
                int minParamIndex = getIndexFromNotConstrainedVariablesWithMinParam(i);
                if(minParamIndex == -1){
                    System.out.println("Node invisible");
                    return;
                }

                //Get not integer variable with min param and adjust it with others.
                nodeVariables[minParamIndex] = limitsList.get(i).getLimitResult();
                for(int j = 0; j < limitsList.get(i).getLimitParameters().length; j++){
                    if(j == minParamIndex)
                        continue;
                    nodeVariables[minParamIndex] -= nodeVariables[j] * limitsList.get(i).getLimitParameters()[j];
                }
            }
        }
    }


    //----------------WARUNKI ZAKOŃCZENIA PODZIAŁU---------------------

    /**
     *
     * @return False if there are not integer variables.
     */
    public boolean isThereAnyNotIntegerVariables(){
        if(getNotIntegerVariableIndexes().size() == 0)
            return false;
        else
            return true;
    }


}



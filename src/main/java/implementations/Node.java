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

    public void setConstraintOnVariableWithMinParam(){
        int minParamIndex = getIndexOfVariableWithMinParam();
        if(!Limits.getVariablesWithConstraints().contains(minParamIndex))
        Limits.addConstraintToVariable(minParamIndex);
    }


    public double [] checkLimits() {

        List<Integer> listaInt = new ArrayList<Integer>();
        List<Integer> paramsWithoutConstraintList = new ArrayList<>();
        List<Limit> limits = Limits.getInstance().getLimitsList();
        for (int i = 0; i < limits.size(); i++){
            //If nodeVariables does not fulfil limit equation.
            if(!limits.get(i).checkIfFulfilEquation(nodeVariables)){

                //remove from limit parameters indexes with constraints.
                List<Integer> constraintIndexes = Limits.getVariablesWithConstraints();//{1, 3}
                //int [] parametersWithoutConstraint = new int [limits.get(i).getLimitParameters().length - constraintIndexes.size()];
                for(int k = 0; k < limits.get(i).getLimitParameters().length; k++){
                    paramsWithoutConstraintList.add(limits.get(i).getLimitParameters()[k]);
                }


                for(int j = 0; j < constraintIndexes.size(); j++){
                    int index = constraintIndexes.get(j);
                    paramsWithoutConstraintList.remove(index);
                }

                listaInt = new ArrayList<Integer>();
                for(int k = 0 ; k < limits.get(i).getLimitParameters().length; k++){
                    if(!constraintIndexes.contains(k));
                    listaInt.add(k);
                }

                // pobierz zmienną z min parametrem ograniczenia
                paramsWithoutConstraintList.stream().min(new CompareInt()).get();


                //Dopasuj tą zmienną, aby pasowała
            }
        }

        return nodeVariables;
    }

    //Dopasuj zmienne,a by spełniały limity i uaktualnij bieżące zmienne.
    /*public double [] adjustedVariables(){
        nodeVariables = getVariablesForHigherNode();
        return nodeVariables;
    }*/

    //----------------WARUNKI ZAKOŃCZENIA PODZIAŁU---------------------

    /**
     *
     * @return False if there are not integer variables.
     */
    private boolean isThereAnyNotIntegerVariables(){
        if(getNotIntegerVariableIndexes().size() == 0)
            return false;
        else
            return true;
    }


}

class CompareInt implements Comparator<Integer>
{
    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 > o2)
            return 1;
        else if(o1 < o2)
            return -1;
        else
            return 0;
    }
}


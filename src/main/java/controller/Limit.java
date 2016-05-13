package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by MaciekBihun on 2016-05-02.
 * Class checks if function variables fulfil limit equation.
 */
public class Limit {

    private String equationSign;
    private Integer [] params;
    private int result;

    public Limit(Integer [] params, String equationSign, int result){
        this.params = params;
        this.equationSign = equationSign;
        this.result = result;
    }

    /**
     *
     * @return Limit parameters entered by user.
     */
    public Integer [] getLimitParameters(){
        return params;
    }

    /**
     *
     * @return Limit result entered by user.
     */
    public int getLimitResult(){
        return result;
    }

    //

    /**
     * Find on which position is placed the first lowest parameter.
     * Of corse parameter 0 is not considered.
     * @return index of parameter with the lowest value.
     */
    public int getMinParamIndex(){
        int index = 0;
        int minValue = params[0];
        for(int i = 1 ; i < params.length; i++){
            if(params[i] < minValue && params[i] != 0){
                index = i;
                minValue = params[i];
            }
        }
        return index;
    }

    /**
     *
     * @return List with indexes of parameters
     * with minimum values.
     */
    public Object [] getMinParamsIndexes(){
        int index = 0;
        int minValue = params[0];
        for(int i = 1 ; i < params.length; i++){
            if(params[i] < minValue && params[i] != 0){
                index = i;
                minValue = params[i];
            }
        }

        List<Integer> minParamIndexes = new ArrayList<Integer>();
        for(int j = 0; j < params.length; j++){
            if(params[j] == params[index]){
                minParamIndexes.add(j);
            }
        }
        return minParamIndexes.toArray();
    }

    /**
     *
     * @param variables
     * @return True if entered variables fulfil limit equation
     */
    public boolean checkIfFulfilEquation(double [] variables){
        double sum = 0;
        if(equationSign.equals(">")){
            for(int i = 0; i < variables.length; i++){
                sum += params[i] * variables[i];
            }
            if(sum > result)
                return true;
        } else if(equationSign.equals("<")){

            for(int i = 0; i < variables.length; i++){
                sum += params[i] * variables[i];
            }
            if(sum < result)
                return true;

        } else if(equationSign.equals(">=")){

            for(int i = 0; i < variables.length; i++){
                sum += params[i] * variables[i];
            }
            if(sum >= result)
                return true;
        } else if(equationSign.equals("<=")){

            for(int i = 0; i < variables.length; i++){
                sum += params[i] * variables[i];
            }
            if(sum <= result)
                return true;
        }
        return false;
    }
}

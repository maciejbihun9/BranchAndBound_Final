import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Limit {

    private String equationSign;
    private int [] params;
    private int result;

    public Limit(int [] params, String equationSign, int result){
        this.params = params;
        this.equationSign = equationSign;
        this.result = result;
    }

    public int [] getLimitParameters(){
        return params;
    }

    public int getLimitResult(){
        return result;
    }

    //Find on which position is placed the first lowest parameter
    //Ofcorse parameter 0 is not considered.
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

    //If there is more than one minimal parameter then
    //return array with that parameters indexes.
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

    /*
    Serve a int array and check weather matches equation input.
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

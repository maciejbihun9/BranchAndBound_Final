package model;

import controller.Limit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-04-30.
 */
public class Limits {

    //List holds variables indexes
    private static List<Integer> variablesWithConstraints = new ArrayList<>();
    //private instance if this class
    private static final Limits limits = new Limits();
    //List holds function limits
    private static final List<Limit> limitsList = new ArrayList<Limit>();

    private Limits(){

    }

    /**
     *
     * @return Variables with constraints.
     */
    public static List<Integer> getVariablesWithConstraints(){
        return variablesWithConstraints;
    }

    public static Limits getInstance(){
        return limits;
    }

    /**
     *
     * @param limit Add function limit to limitList.
     */
    public static void addLimit(Limit limit){
        limitsList.add(limit);
    }

    /**
     *
     * @return Gets list with function limits
     */
    public List<Limit> getLimitsList(){
        return limitsList;
    }

    /**
     *
     * @param variableIndex Add constraint to a variable index.
     *                      That index cannot be used for next branches
     */
    public static void addConstraintToVariable(int variableIndex){
        if(!variablesWithConstraints.contains(variableIndex))
        variablesWithConstraints.add(variableIndex);
    }

}

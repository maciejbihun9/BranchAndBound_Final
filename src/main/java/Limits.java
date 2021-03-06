import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-04-30.
 */
public class Limits {

    //List holds variables indexes
    private static List<Integer> variablesWithConstraints = new ArrayList<>();

    private static final Limits limits = new Limits();
    private static final List<Limit> limitsList = new ArrayList<Limit>();

    private Limits(){

    }

    public static List<Integer> getVariablesWithConstraints(){
        return variablesWithConstraints;
    }

    public static Limits getInstance(){
        return limits;
    }

    public static void addLimit(Limit limit){
        limitsList.add(limit);
    }

    public List<Limit> getLimitsList(){
        return limitsList;
    }

    public static void addConstraintToVariable(int variableIndex){
        variablesWithConstraints.add(variableIndex);
    }

    //Check wether variable index already exist in list.
    public static boolean checkVariableForConstrints(int variableIndex){
        for(Integer variable : variablesWithConstraints){
            if(variable == variableIndex){
                return true;
            }
        }
        return false;
    }
}

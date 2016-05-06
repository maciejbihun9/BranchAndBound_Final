package implementations;

import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Parameters {

    private static final Parameters parameters = new Parameters();
    private static List <Integer> functionParameters;
    private Parameters(){

    }

    public static Parameters getInstance(){
        return parameters;
    }

    public static void setFunctionParameters(List<Integer> params){
        functionParameters = params;
    }

    /**
     *
     * @return Array with function parameters.
     */
    public List<Integer> getFunctionParameters(){
        return functionParameters;
    }
}

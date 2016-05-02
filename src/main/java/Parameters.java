/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Parameters {

    private static final Parameters parameters = new Parameters();
    private static int [] functionParameters;
    private Parameters(){

    }

    public static Parameters getInstance(){
        return parameters;
    }

    public static void setFunctionParameters(int [] params){
        functionParameters = params;
    }

    public static int [] getFunctionParameters(){
        return functionParameters;
    }
}

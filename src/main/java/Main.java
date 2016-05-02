/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class Main {

    public static void main(String[] args) {
        //Enter function parameters.
        int [] functionParameters = {2, 5, 1, 3, 7, 2};

        //Set function parameters only once for entire application.
        Parameters.setFunctionParameters(functionParameters);

        //Enter starting variables.
        double [] startingVariables = {3.4, 12.3, 5, 2, 2.2, 6};

        //Enter function limits.
        //Length of the array must be the same as functionParameters.
        int [] limitParams1 = {2, 3, 0, 0 ,4, 0};
        Limit limit1 = new Limit(limitParams1, ">", 25);

        int [] limitParams2 = {4, 1 ,3,0, 5, 2};
        Limit limit2 = new Limit(limitParams2, ">=", 15);

        int [] limitParams3 = {2,2, 0, 3 ,4, 3};
        Limit limit3 = new Limit(limitParams3, ">=", 25);

        //Add limits to collection.
        Limits.addLimit(limit1);
        Limits.addLimit(limit2);
        Limits.addLimit(limit3);


        //Set singleton MainNode startingVariables.
        MainNode.getInstance().setStartingVariables(startingVariables);
    }
}

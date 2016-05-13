import controller.Limit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class LimitTest {

    //Enter function limits.
    //Length of the array must be the same as functionParameters.
    Integer [] limitParams1 = {2, 3, 2, 0 ,1, 0, 1, 1};


    //Enter starting variables.
    double [] startingVariables = {3.4, 12.3, 5, 2, 2.2, 6};


    String equationSign = ">=";
    Limit limit;
    @Before
    public void initializeLimitObjects(){
        int limitResult = 25;
        limit = new Limit(limitParams1, equationSign, limitResult);
    }

    @Test
    public void getMinParamTest(){
        assertEquals(limit.getMinParamIndex(), 4);
    }

    @Test
    public void getMinParamsTest(){
        Object [] minParams = {4, 6, 7};
        for(int i = 0; i < limit.getMinParamsIndexes().length; i++){
            System.out.println(limit.getMinParamsIndexes()[i]);
        }
        assertArrayEquals(limit.getMinParamsIndexes(), minParams);
    }

    @Test
    public void fulfilEquation(){
        assertEquals(limit.checkIfFulfilEquation(startingVariables), true);

    }
}

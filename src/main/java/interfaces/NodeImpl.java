package interfaces;

/**
 * Created by MaciekBihun on 2016-05-03.
 */
public interface NodeImpl {

    int getId();
    void branchNode();
    boolean checkLimits();
    double [] getLowerNodeVariables();
    double [] getHigherNodeVariables();


}

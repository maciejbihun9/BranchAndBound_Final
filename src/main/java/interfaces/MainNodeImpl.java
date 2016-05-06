package interfaces;
import implementations.Node;

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public interface MainNodeImpl {
   Node with(double [] startingVariables);
    int getMainNodeFunctionSum();
    void startBranch();
    int getIndexOfVariableWithMinParam();

}

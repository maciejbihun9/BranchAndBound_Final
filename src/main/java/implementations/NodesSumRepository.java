package implementations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaciekBihun on 2016-05-03.
 */
public class NodesSumRepository {

    private static final NodesSumRepository repo = new NodesSumRepository();
    private static Map<Integer, Double> nodeResults = new HashMap<>();
    private NodesSumRepository(){

    }

    /**
     *
     * @return Map with results of the nodes sum functions.
     */
    public Map<Integer, Double> getNodeResults(){
        return nodeResults;
    }

    /**
     * Put node result of the sum function to the map.
     */
    public void putNodeResult(int id, double nodeResult){
        nodeResults.put(id, nodeResult);
    }

    public static NodesSumRepository getInstance(){
        return repo;
    }


}

/**
 * Created by MaciekBihun on 2016-05-02.
 */
public class MainNode {

    private static final MainNode mainNode = new MainNode();

    private double [] startingVariables;
    private MainNode(){

    }

    public static MainNode getInstance(){
        return mainNode;
    }

    public void setStartingVariables(double [] startingVariables){
        this.startingVariables = startingVariables;
    }


}

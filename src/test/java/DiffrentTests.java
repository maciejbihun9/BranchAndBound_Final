import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-03.
 */
public class DiffrentTests {

    List<Integer> listaInt;
    @Before
    public void initialize(){
        listaInt = new ArrayList<>();
        listaInt.add(3);
        listaInt.add(6);
        listaInt.add(2);
        listaInt.add(8);
        listaInt.add(7);
    }

    @Test
    public void testList(){
        int miin = listaInt.stream().min(new CompareInt2()).get();
        System.out.println(miin);
    }
}

class CompareInt2 implements Comparator<Integer>
{


    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 > o2)
            return 1;
        else if (o1 < o2)
            return -1;
        else
            return 0;
    }
}



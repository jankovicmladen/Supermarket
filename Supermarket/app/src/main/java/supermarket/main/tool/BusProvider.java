package supermarket.main.tool;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by cubesschool2 on 9/14/16.
 */
public class BusProvider {

    private static Bus bus;

    public static Bus getInstance(){
        if(bus == null){
            bus = new Bus();
        }

        return bus;
    }

}

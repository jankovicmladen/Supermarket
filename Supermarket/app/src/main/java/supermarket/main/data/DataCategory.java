package supermarket.main.data;

import java.util.ArrayList;

/**
 * Created by cubesschool2 on 9/12/16.
 */
public class DataCategory {

    public String id;
    public String name;
    public ArrayList<DataCategory> subcategories;

    @Override
    public String toString() {
        return name + subcategories.toString();
    }
}

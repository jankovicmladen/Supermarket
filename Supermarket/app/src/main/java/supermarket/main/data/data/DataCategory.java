package supermarket.main.data.data;

import java.util.ArrayList;

/**
 * Created by cubesschool2 on 9/12/16.
 */
public class DataCategory {

    public String id;
    public String name;
    public ArrayList<DataCategory> subcategories;

    public DataCategory(String name){
        this.name = name;
        this.subcategories = new ArrayList<>();
        this.id = "-1";
    }

    @Override
    public String toString() {
        return name + subcategories.toString();

    }
}

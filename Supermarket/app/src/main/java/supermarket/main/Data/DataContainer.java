package supermarket.main.data;

import java.util.ArrayList;

/**
 * Created by cubesschool2 on 9/9/16.
 */
public class DataContainer {
    public static String TOKEN;

    public static ArrayList<DataCategory> categories;
    public static ArrayList<DataCity> cities;
    public static ArrayList<String> stringsCities;


    public static ArrayList<String> cityToString(ArrayList<DataCity> cities){
        ArrayList<String> result = new ArrayList<>();

        for (DataCity city :cities){
            result.add(city.toString());
        }

        return result;
    }
}

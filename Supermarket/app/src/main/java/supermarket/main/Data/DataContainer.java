package supermarket.main.data;

import java.util.ArrayList;

import supermarket.main.data.response.ResponseDataUser;
import supermarket.main.data.response.ResponseProducts;
import supermarket.main.data.response.ResponseUser;

/**
 * Created by cubesschool2 on 9/9/16.
 */
public class DataContainer {
    public static String TOKEN;

    public static ArrayList<DataCategory> categories;
    public static ArrayList<DataCity> cities;
    public static ArrayList<String> stringsCities;
    public static ArrayList<DataReservation> reservations;

    public static ResponseDataUser user;
    public static String token;
    public static String loginToken;

    public static ArrayList<DataProduct> products;

    public static ArrayList<String> cityToString(ArrayList<DataCity> cities){
        ArrayList<String> result = new ArrayList<>();

        for (DataCity city :cities){
            result.add(city.toString());
        }

        return result;
    }
}

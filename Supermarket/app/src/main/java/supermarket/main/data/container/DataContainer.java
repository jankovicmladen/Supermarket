package supermarket.main.data.container;

import java.util.ArrayList;

import supermarket.main.data.data.DataCategory;
import supermarket.main.data.data.DataCity;
import supermarket.main.data.data.DataProduct;
import supermarket.main.data.data.DataReservation;
import supermarket.main.data.data.DataSingleProduct;
import supermarket.main.data.response.ResponseDataUser;

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
    public static DataSingleProduct product;
    public static ArrayList<String> cart = new ArrayList<>();

    public static ArrayList<String> cityToString(ArrayList<DataCity> cities){
        ArrayList<String> result = new ArrayList<>();

        for (DataCity city :cities){
            result.add(city.toString());
        }

        return result;
    }
}

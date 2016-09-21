package supermarket.main.data.container;

import android.widget.Toast;

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
    public static ArrayList<DataProduct> cart = new ArrayList<>();

    public static ArrayList<String> cityToString(ArrayList<DataCity> cities) {
        ArrayList<String> result = new ArrayList<>();

        for (DataCity city : cities) {
            result.add(city.toString());
        }

        return result;
    }


    public static boolean addToCart(int id) {
        DataProduct productForCart = null;

        for (DataProduct product : products) {
            if (Integer.parseInt(product.id) == id) {
                productForCart = product;
            }
        }
        if (productForCart != null) {
            int stock = Integer.parseInt(productForCart.stock);
            if (stock >= 1) {
                if(cart.contains(productForCart)){
                    productForCart.amount++;
                }else{
                    productForCart.amount=1;
                    cart.add(productForCart);
                }

                //cart.add(productForCart);
                stock--;
                productForCart.stock = stock + "";
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean removeFromCart(String id){
        DataProduct productForCart = null;

        for (DataProduct product : products) {
            if (product.id.equalsIgnoreCase(id)) {
                productForCart = product;
            }
        }

        if(productForCart!=null){
            if (cart.contains(productForCart)){
                int stock = Integer.parseInt(productForCart.stock);
                if(productForCart.amount>1){
                    productForCart.amount--;
                    stock++;
                    productForCart.stock = stock+"";
                    return true;
                }else {
                    cart.remove(productForCart);
                    productForCart.amount=0;
                    stock++;
                    productForCart.stock=stock+"";
                    return true;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}

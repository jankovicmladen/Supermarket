package supermarket.main.data.container;

import android.widget.Toast;

import java.util.ArrayList;

import supermarket.main.data.data.DataAddress;
import supermarket.main.data.data.DataCategory;
import supermarket.main.data.data.DataCity;
import supermarket.main.data.data.DataProduct;
import supermarket.main.data.data.DataReservation;
import supermarket.main.data.data.DataSingleProduct;
import supermarket.main.data.response.ResponseDataUser;
import supermarket.main.ui.activity.CartActivity;

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

    public static ArrayList<Integer> listFavorits;

    public static DataAddress address;
    public static ArrayList<DataProduct> products;
    public static ArrayList<DataProduct> serchList = new ArrayList<>();

    public static DataSingleProduct product;
    public static ArrayList<DataProduct> cart = new ArrayList<>();

    public static Double totalPrice = 0.0;

    public static ArrayList<String> cityToString(ArrayList<DataCity> cities) {
        ArrayList<String> result = new ArrayList<>();

        for (DataCity city : cities) {
            result.add(city.toString());
        }

        return result;
    }


    private static boolean tmp2 = false;

    public static boolean addToCart(int id) {
        DataProduct productForCart = null;

        for (DataProduct product : products) {
            if (Integer.parseInt(product.id) == id) {
                productForCart = product;
            }
        }

        if(cart.size()==0){
            tmp2=false;
        }

        for (DataProduct product : cart) {
            if (Integer.parseInt(product.id) == id) {
                productForCart = product;
                tmp2=true;
            }else {
                tmp2=false;
            }
        }

        if (productForCart != null) {
            int stock = Integer.parseInt(productForCart.stock);
            if (stock >= 1) {
              //  if(cart.contains(productForCart)){
                if(tmp2){

                    productForCart.amount++;
                    totalPrice += Double.parseDouble(productForCart.price);
                    if(CartActivity.mTvTotalPrice!=null) {
                        CartActivity.mTvTotalPrice.setText("Ukupno: " + totalPrice);
                    }
                }else{
                    productForCart.amount=1;
                    cart.add(productForCart);
                    totalPrice += Double.parseDouble(productForCart.price);
                    if(CartActivity.mTvTotalPrice!=null) {
                        CartActivity.mTvTotalPrice.setText("Ukupno: " + totalPrice);
                    }
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
        for (DataProduct product : cart) {

            if (product.id.equalsIgnoreCase(id)) {
                productForCart = product;
                tmp2=true;
            }else {
                tmp2=false;
            }
        }
        if(productForCart!=null){
            if (tmp2){
                int stock = Integer.parseInt(productForCart.stock);
                if(productForCart.amount>1){
                    productForCart.amount--;
                    totalPrice -= Double.parseDouble(productForCart.price);
                    if(CartActivity.mTvTotalPrice!=null) {
                        CartActivity.mTvTotalPrice.setText("Ukupno: " + totalPrice);
                    }
                    stock++;
                    productForCart.stock = stock+"";
                    return true;
                }else {
                    cart.remove(productForCart);
                    productForCart.amount=0;
                    totalPrice -= Double.parseDouble(productForCart.price);
                    if(CartActivity.mTvTotalPrice!=null) {
                        CartActivity.mTvTotalPrice.setText("Ukupno: " + totalPrice);
                    }
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

    public static void setFavorits(ArrayList<Integer> favList){
        for(DataProduct product : DataContainer.products){
            for (int i : favList){
                if(product.id.equalsIgnoreCase(i+"")){
                    product.favorit = true;
                }
            }
        }

    }

}

package supermarket.main.constant;


import android.content.Context;

import java.util.ArrayList;

import supermarket.main.data.container.DataContainer;

/**
 * Created by cubesschool2 on 9/9/16.
 */
public class Constant {

    public static String APPLICATION_PASSWORD = "VRf68vuFNAXWXjTg@!";
    public static String APPLICATION_USERNAME = "phone";
    public static String PASSWORD = "mladenjankovic";

    private static String BASE_URL = "http://shop.cubes.rs/";

    public static String GRT_TOKEN_URL = BASE_URL + "phone-home-gettoken";
    public static String LOGIN_URL = BASE_URL + "phone-user";
    public static String SIGNUP_URL = BASE_URL + "phone-signup";
    public static String FORGOT_PASSWORD_URL = BASE_URL + "phone-user-forgotpassword";
    public static String CITY_URL = BASE_URL + "phone-helper-places?token="+ DataContainer.TOKEN;
    public static String RESERVATION_URL = BASE_URL + "phone-helper-reservation?token=" + DataContainer.TOKEN;

    public static String CATEGORY_URL = BASE_URL + "phone-categories";
    public static String CATEGORY_SEARCH_URL = BASE_URL + "phone-products?token="+DataContainer.TOKEN+"&search=1&limit=10&category=";

    public static String PRODUCTS = BASE_URL + "phone-products?token=" + DataContainer.loginToken;
    public static String SINGLE_PRODUCT = BASE_URL + "phone-products-single?token=" + DataContainer.loginToken;

    public static String ORDER = BASE_URL + "phone-order";


    //POSTMAN za proveru post metoda

}

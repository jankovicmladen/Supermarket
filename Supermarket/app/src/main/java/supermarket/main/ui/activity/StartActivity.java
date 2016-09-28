package supermarket.main.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataCategory;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseCity;
import supermarket.main.data.response.ResponseProducts;
import supermarket.main.data.response.ResponseReservation;
import supermarket.main.data.response.ResponseToken;
import supermarket.main.data.response.ResponseUser;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;
import supermarket.main.ui.fragment.LoginFragment;

public class StartActivity extends ActivityWithMessage {

    private final String REQUEST_TAG = "Start_activity";
    private GsonReguest<ResponseToken> mRequestToken;
    private GsonReguest<ResponseCategory> mREquestCategory;
    private GsonReguest<ResponseCity> mRequestCityu;
    private GsonReguest<ResponseReservation> mRequestReservation;
    private GsonReguest<ResponseUser> mRequestLogin;
    private GsonReguest<ResponseProducts> responseProducts;
    private int chek = 0;

    private String username, password, password_dec;
    private boolean staylogin;


    private int check = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        SharedPreferences settings = getSharedPreferences(LoginFragment.PREFS_NAME, 0);
        username = settings.getString(LoginFragment.USERNAME, "");
        password = settings.getString(LoginFragment.PASSWORD, "");
        staylogin = settings.getBoolean(LoginFragment.STAY_LOGIN,false);

        try {
            if(!password.equalsIgnoreCase("")) {
                password_dec = AESCrypt.decrypt(Constant.PASSWORD, password);
            }
        }catch (GeneralSecurityException e){
            //handle error - could be due to incorrect password or tampered encryptedMsg
        }

        mRequestToken = new GsonReguest<ResponseToken>(Constant.GRT_TOKEN_URL + "?username=" + Constant.APPLICATION_USERNAME +
                "&password=" + Constant.APPLICATION_PASSWORD,
                Request.Method.GET, ResponseToken.class, new Response.Listener<ResponseToken>() {
            @Override
            public void onResponse(ResponseToken response) {
                DataContainer.TOKEN = response.data.results.token;
                check++;
                checkOK(check);
                DataLoader.addRequest(getApplicationContext(), mREquestCategory, REQUEST_TAG);
                DataLoader.addRequest(getApplicationContext(), mRequestCityu, REQUEST_TAG);
                DataLoader.addRequest(getApplicationContext(), mRequestReservation, REQUEST_TAG);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                BusProvider.getInstance().post(new MessageObject());
            }
        });


        DataContainer.categories = new ArrayList<>();
        mREquestCategory = new GsonReguest<ResponseCategory>(Constant.CATEGORY_URL + "?token=" +
                DataContainer.TOKEN, Request.Method.GET, ResponseCategory.class,
                new Response.Listener<ResponseCategory>() {
                    @Override
                    public void onResponse(ResponseCategory response) {
                        DataContainer.categories.add(new DataCategory("Home"));
                        DataContainer.categories.addAll(response.data.results);
                        DataContainer.categories.add(new DataCategory("Podesavanja"));
                        DataContainer.categories.add(new DataCategory("Profil"));
                        DataContainer.categories.add(new DataCategory("Odjavi se"));
                        check++;
                        checkOK(check);
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BusProvider.getInstance().post(new MessageObject());
            }
        });

        mRequestCityu = new GsonReguest<ResponseCity>(Constant.CITY_URL, Request.Method.GET, ResponseCity.class,
                new Response.Listener<ResponseCity>() {
                    @Override
                    public void onResponse(ResponseCity response) {
                        DataContainer.cities = response.data.results.townships;
                        check++;
                        checkOK(check);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BusProvider.getInstance().post(new MessageObject());
            }
        });


        mRequestReservation = new GsonReguest<ResponseReservation>(Constant.RESERVATION_URL,
                Request.Method.GET, ResponseReservation.class,
                new Response.Listener<ResponseReservation>() {
                    @Override
                    public void onResponse(ResponseReservation response) {
                        DataContainer.reservations = response.data.results;
                        check++;
                        checkOK(check);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BusProvider.getInstance().post(new MessageObject());
            }
        });

        mRequestLogin = new GsonReguest<ResponseUser>(Constant.LOGIN_URL,
                Request.Method.POST, ResponseUser.class,
                new Response.Listener<ResponseUser>() {
                    @Override
                    public void onResponse(ResponseUser response) {
                        DataContainer.user = response.data.results;
                        DataContainer.token = response.data.token;
                        DataContainer.loginToken = response.data.login_token;

                        DataLoader.addRequest(getApplicationContext(), responseProducts, REQUEST_TAG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("email", username);
                params.put("password", password_dec);
                params.put("token", DataContainer.TOKEN);

                return params;
            }
        };

        responseProducts = new GsonReguest<ResponseProducts>(
                Constant.PRODUCTS, Request.Method.GET, ResponseProducts.class,
                new Response.Listener<ResponseProducts>() {
                    @Override
                    public void onResponse(ResponseProducts response) {
                        Log.i("response", response.data.status);

                        DataContainer.products = response.data.results;
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        DataLoader.addRequest(getApplicationContext(), mRequestToken, REQUEST_TAG);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataLoader.cancelRequest(getApplicationContext(), REQUEST_TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            //    WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private synchronized boolean checkOK(int param) {
        if (param == 4) {
            if(staylogin){
                DataLoader.addRequest(getApplicationContext(), mRequestLogin, REQUEST_TAG);
                return true;
            }else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            }
        } else return false;
    }
}

package supermarket.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseCity;
import supermarket.main.data.response.ResponseReservation;
import supermarket.main.data.response.ResponseToken;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;

public class StartActivity extends ActivityWithMessage {

    private final String REQUEST_TAG = "Start_activity";
    private GsonReguest<ResponseToken> mRequestToken;
    private GsonReguest<ResponseCategory> mREquestCategory;
    private GsonReguest<ResponseCity> mRequestCityu;
    private GsonReguest<ResponseReservation> mRequestReservation;
    private int chek = 0;


    private int check = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start_page);

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

        mREquestCategory = new GsonReguest<ResponseCategory>(Constant.CATEGORY_URL + "?token=" +
                DataContainer.TOKEN, Request.Method.GET, ResponseCategory.class,
                new Response.Listener<ResponseCategory>() {
                    @Override
                    public void onResponse(ResponseCategory response) {
                        DataContainer.categories = response.data.results;
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


        DataLoader.addRequest(getApplicationContext(), mRequestToken, REQUEST_TAG);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataLoader.cancelRequest(getApplicationContext(), REQUEST_TAG);
    }

    private synchronized boolean checkOK(int param) {
        if (param == 4) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        } else return false;
    }
}

package supermarket.main.ui.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.data.DataContainer;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseCity;
import supermarket.main.data.response.ResponseToken;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;

public class StartActivity extends AppCompatActivity {

    private final String REQUEST_TAG = "Start_activity";
    private GsonReguest<ResponseToken> mRequestToken;
    private GsonReguest<ResponseCategory> mREquestCategory;
    private GsonReguest<ResponseCity> mRequestCityu;


    private int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
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
//                        Toast.makeText(getApplicationContext(),DataContainer.categories.toString(),Toast.LENGTH_LONG).show();
//                        DataLoader.addRequest(getApplicationContext(),mRequestCityu, REQUEST_TAG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        if (param == 3) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        } else return false;
    }
}

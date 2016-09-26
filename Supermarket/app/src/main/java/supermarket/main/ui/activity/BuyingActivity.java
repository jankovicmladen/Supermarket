package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import supermarket.main.R;
import supermarket.main.adapters.CartRecyclerViewAdapret;
import supermarket.main.adapters.FinalBuyingAdapter;
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataProduct;
import supermarket.main.data.response.ResponseOrder;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;

public class BuyingActivity extends ActivityWithMessage {
private final String TAG = "order_tag";

    private ImageView mIvBack;
    private TextViewFont mTvAddress, mTvCity;
    public static TextViewFont mTvTotalPrice;
    private RecyclerView mRecyclerView;
    private Button mBtnBuy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);

        iniComponents();
        addListeners();
    }

    private void iniComponents() {
        mIvBack = (ImageView) findViewById(R.id.back);

        mTvAddress = (TextViewFont) findViewById(R.id.address);
        mTvAddress.setText(DataContainer.user.address + " " + DataContainer.user.street_number);

        mTvCity = (TextViewFont) findViewById(R.id.city);
        mTvCity.setText(DataContainer.user.postalcode + " " + DataContainer.user.city);

        mTvTotalPrice = (TextViewFont) findViewById(R.id.total_price);
        mTvTotalPrice.setText("Ukupno: "+DataContainer.totalPrice);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        FinalBuyingAdapter adapret = new FinalBuyingAdapter(getApplicationContext(),DataContainer.cart);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(adapret);

        mBtnBuy = (Button) findViewById(R.id.btn_buy);
    }

    private void addListeners() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                sentOrderList();

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public void sentOrderList(){

        GsonReguest<ResponseOrder> gsonReguest = new GsonReguest<ResponseOrder>(
                Constant.ORDER, Request.Method.POST, ResponseOrder.class,
                new Response.Listener<ResponseOrder>() {
                    @Override
                    public void onResponse(ResponseOrder response) {
                        BusProvider.getInstance().post(new MessageObject(R.string.uspesna_kupovina, MessageObject.MESSAGE_INFO));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BusProvider.getInstance().post(new MessageObject(R.string.error_message,MessageObject.MESSAGE_ERROR));
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() {

                String aricles="";
                for(DataProduct dataProduct : DataContainer.cart){
                    aricles = new String(aricles).concat(dataProduct.id + "," + dataProduct.amount + ",0" + "|");
                }

                Map<String,String> params = new HashMap<>();
                params.put("token", DataContainer.TOKEN);
                params.put("login_token", DataContainer.loginToken);
                params.put("buyer_id", DataContainer.user.id);
                params.put("payment_type", AddressActivity.nacinPlacanja);
                params.put("articles", aricles);
                params.put("new_address", AddressActivity.nacinPlacanja + "");
                params.put("street", DataContainer.address.street);
                params.put("number", DataContainer.address.street_number);
                params.put("appartement", DataContainer.address.appartment);
                params.put("floor", DataContainer.address.floor);
                params.put("entrance", DataContainer.address.entrance);
                params.put("city", DataContainer.address.city);
                params.put("postal_code", DataContainer.address.postalcode);
                return params;

            }
        };

       DataLoader.addRequest(getApplicationContext(),gsonReguest,TAG);
    }
}

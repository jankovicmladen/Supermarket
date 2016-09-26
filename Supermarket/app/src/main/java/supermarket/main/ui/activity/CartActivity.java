package supermarket.main.ui.activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
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
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.EditTextFont;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.response.ResponseOrder;
import supermarket.main.networking.GsonReguest;

public class CartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ImageView mIvBack;
    public static TextViewFont mTvTotalPrice;
    private RecyclerView mRecyclerView;
    private Button mBtnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        iniComponents();
        addListeners();
    }


    private void iniComponents() {

        mIvBack = (ImageView) findViewById(R.id.back);

        mTvTotalPrice = (TextViewFont) findViewById(R.id.total_price);
        mTvTotalPrice.setText("Ukupno: "+DataContainer.totalPrice);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        CartRecyclerViewAdapret adapret = new CartRecyclerViewAdapret(getApplicationContext(),DataContainer.cart,this);

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
                startActivity(new Intent(getApplicationContext(),AddressActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}

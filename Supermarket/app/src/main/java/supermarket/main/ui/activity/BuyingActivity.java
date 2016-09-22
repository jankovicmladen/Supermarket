package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import supermarket.main.R;
import supermarket.main.adapters.CartRecyclerViewAdapret;
import supermarket.main.adapters.FinalBuyingAdapter;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;

public class BuyingActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private TextViewFont mTvAddress, mTvCity;
    public static TextViewFont mTvTotalPrice;
    private RecyclerView mRecyclerView;
    private Button mBtnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

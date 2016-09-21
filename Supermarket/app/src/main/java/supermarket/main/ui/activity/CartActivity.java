package supermarket.main.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import supermarket.main.R;
import supermarket.main.adapters.CartRecyclerViewAdapret;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;

public class CartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ImageView mIvBack, mIvBuyerInfo;
    private TextViewFont mTvAddress, mTvCity;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        iniComponents();
        addListeners();
    }


    private void iniComponents() {

        mIvBack = (ImageView) findViewById(R.id.back);
        mIvBuyerInfo = (ImageView) findViewById(R.id.right_buyer);

        mTvAddress = (TextViewFont) findViewById(R.id.address);
        mTvAddress.setText(DataContainer.user.address + " " + DataContainer.user.street_number);

        mTvCity = (TextViewFont) findViewById(R.id.city);
        mTvCity.setText(DataContainer.user.postalcode + " " + DataContainer.user.city);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        CartRecyclerViewAdapret adapret = new CartRecyclerViewAdapret(getApplicationContext(),DataContainer.cart,this);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(adapret);
    }

    private void addListeners() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

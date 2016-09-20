package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.response.ResponseSingleProduct;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;
import supermarket.main.adapters.RecyclerAdapter;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final String REQUEST_TAG= "load_single_product";
    private RecyclerView mRecyclerView;
    RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new RecyclerAdapter(getApplicationContext(), DataContainer.products, this);
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        GsonReguest<ResponseSingleProduct> reguest = new GsonReguest<ResponseSingleProduct>(
                Constant.SINGLE_PRODUCT + "&id=" + adapter.getItemId(position) + "",
                Request.Method.GET,
                ResponseSingleProduct.class,
                new Response.Listener<ResponseSingleProduct>() {
                    @Override
                    public void onResponse(ResponseSingleProduct response) {
                        DataContainer.product = response.data.results;
                        startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        DataLoader.addRequest(getApplicationContext(), reguest, REQUEST_TAG);
    }
}

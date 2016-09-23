package supermarket.main.ui.activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;

public class HomeActivity extends ActivityWithMessage
        implements AdapterView.OnItemClickListener {

    private final String REQUEST_TAG = "load_single_product";
    private RecyclerView mRecyclerView;
    RecyclerAdapter adapter;
    private ImageView mIvShopingCart;
    private ImageView mIvMenu;
    private ListView mLvMenu;
    private DrawerLayout mDrawerLayout;
    Animation animation;// = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_image);
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniComponents();

        addListeners();

    }

    private void addListeners() {
        mIvShopingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mLvMenu);
            }
        });

    }

    private void iniComponents() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_image);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new RecyclerAdapter(getApplicationContext(), DataContainer.products, this);
        mRecyclerView.setAdapter(adapter);

        mIvShopingCart = (ImageView) findViewById(R.id.shoping_cart);
        mIvMenu = (ImageView) findViewById(R.id.menu);
        mLvMenu = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.add, R.string.add) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mIvMenu.startAnimation(animation);
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.image) {
            GsonReguest<ResponseSingleProduct> reguest = new GsonReguest<ResponseSingleProduct>(
                    Constant.SINGLE_PRODUCT + "&id=" + adapter.getItemId(position) + "",
                    Request.Method.GET,
                    ResponseSingleProduct.class,
                    new Response.Listener<ResponseSingleProduct>() {
                        @Override
                        public void onResponse(ResponseSingleProduct response) {
                            DataContainer.product = response.data.results;
                            startActivity(new Intent(getApplicationContext(), ProductActivity.class));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );

            DataLoader.addRequest(getApplicationContext(), reguest, REQUEST_TAG);
        } else if (view.getId() == R.id.add) {
            if (DataContainer.addToCart((int) adapter.getItemId(position))) {
                BusProvider.getInstance().post(new MessageObject(R.string.added,MessageObject.MESSAGE_INFO));
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

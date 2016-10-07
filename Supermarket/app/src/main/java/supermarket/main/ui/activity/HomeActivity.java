package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import supermarket.main.R;
import supermarket.main.adapters.MenuAdapter;
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataCategory;
import supermarket.main.data.data.DataProduct;
import supermarket.main.data.response.ResponseAddWishList;
import supermarket.main.data.response.ResponseCategory;
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
    private RelativeLayout mLvMenu;
    private DrawerLayout mDrawerLayout;
    Animation animation;// = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_image);
    ActionBarDrawerToggle actionBarDrawerToggle;

    private TextViewFont mTvUsenName;
    private TextViewFont mTvUserMail;

    private ExpandableListView mExpandableListView;


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

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (DataContainer.categories.get(groupPosition).subcategories.size() > 0) {

                } else {
                    if (DataContainer.categories.get(groupPosition).name.equalsIgnoreCase("Home")) {
                        onBackPressed();
                    } else if (DataContainer.categories.get(groupPosition).name.equalsIgnoreCase("Podesavanja")) {

                    } else if (DataContainer.categories.get(groupPosition).name.equalsIgnoreCase("Profil")) {
                        startActivity(new Intent(getApplicationContext(),ProfilActivity.class));

                    } else if (DataContainer.categories.get(groupPosition).name.equalsIgnoreCase("Odjavi se")) {

                    } else {
                        GsonReguest<ResponseCategory> gsonCategoryRequest = new GsonReguest<ResponseCategory>(
                                Constant.CATEGORY_SEARCH_URL + DataContainer.categories.get(groupPosition).id, Request.Method.GET, ResponseCategory.class,
                                new Response.Listener<ResponseCategory>() {
                                    @Override
                                    public void onResponse(ResponseCategory response) {
                                        Toast.makeText(getApplicationContext(), response.data.status, Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        );

                        DataLoader.addRequest(getApplicationContext(), gsonCategoryRequest, "categories");
                    }
                }

                return false;
            }
        });
    }

    private void iniComponents() {

        mExpandableListView = (ExpandableListView) findViewById(R.id.list);
        MenuAdapter listAdapter = new MenuAdapter(this, DataContainer.categories);
        mExpandableListView.setAdapter(listAdapter);

        mTvUsenName = (TextViewFont) findViewById(R.id.name);
        mTvUsenName.setText(DataContainer.user.first_name + " " + DataContainer.user.last_name);
        mTvUserMail = (TextViewFont) findViewById(R.id.email);
        mTvUserMail.setText(DataContainer.user.email);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_image);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new RecyclerAdapter(getApplicationContext(), DataContainer.products, this);
        mRecyclerView.setAdapter(adapter);

        mIvShopingCart = (ImageView) findViewById(R.id.shoping_cart);
        mIvMenu = (ImageView) findViewById(R.id.menu);
        mLvMenu = (RelativeLayout) findViewById(R.id.left_drawer);
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

        if (view.getId() == R.id.layout_star || view.getId() == R.id.star) {
            final DataProduct product = DataContainer.products.get(position);
            if (DataContainer.products.get(position).favorit) {
                DataContainer.products.get(position).favorit = false;
            } else {
                DataContainer.products.get(position).favorit = true;
            }
            adapter.notifyDataSetChanged();


            GsonReguest<ResponseAddWishList> gsonReguest = new GsonReguest<ResponseAddWishList>(
                    Constant.URL_FAVOURITES_ADD,
                    Request.Method.POST,
                    ResponseAddWishList.class,
                    new Response.Listener<ResponseAddWishList>() {
                        @Override
                        public void onResponse(ResponseAddWishList response) {
                            DataContainer.listFavorits = response.data.results;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"greska",Toast.LENGTH_LONG).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> requestParams = new HashMap<>();
                    requestParams.put("user_id", DataContainer.user.id);
                    requestParams.put("item_id",product.id);
                    requestParams.put("token",DataContainer.loginToken);
                    return requestParams;
                }
            };

            DataLoader.addRequest(getApplicationContext(),gsonReguest, REQUEST_TAG);
        } else if (view.getId() == R.id.image) {
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
                BusProvider.getInstance().post(new MessageObject(R.string.added, MessageObject.MESSAGE_INFO));
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

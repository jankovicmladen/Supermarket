package supermarket.main.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import supermarket.main.R;
import supermarket.main.customComponents.DividerItemDecoration;
import supermarket.main.data.DataContainer;
import supermarket.main.tool.RecyclerAdapter;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyeler_view);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), DataContainer.products);
        mRecyclerView.setAdapter(adapter);

//       
    }
}

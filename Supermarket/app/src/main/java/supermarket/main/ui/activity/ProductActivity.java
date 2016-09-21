package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataSingleProduct;

public class ProductActivity extends AppCompatActivity {

    private ImageView mImageView, mIvBack;
    private TextViewFont mPrice, mName, mMaterial, mSize, mColor;
    private Button mBtnAddToCart;
    private DataSingleProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product = DataContainer.product;

        iniComponenets();
        addListeners();
    }

    private void iniComponenets(){

        mIvBack = (ImageView) findViewById(R.id.back);

        mImageView = (ImageView) findViewById(R.id.image);
        Glide.with(getApplicationContext()).load(product.thumb330)
                .fitCenter()
                .into(mImageView);

        mPrice = (TextViewFont) findViewById(R.id.price);
        mPrice.setText(product.price);

        mName = (TextViewFont) findViewById(R.id.name);
        mName.setText(product.name);

        mMaterial = (TextViewFont) findViewById(R.id.material);
        mMaterial.setText("Materijal: " + product.material);

        mSize = (TextViewFont) findViewById(R.id.size);
        mSize.setText("Velicina: ");

        mColor = (TextViewFont) findViewById(R.id.color);
        mColor.setText("Boja: " );

        mBtnAddToCart =(Button) findViewById(R.id.btn_add);
    }

    private void addListeners(){
        mBtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataContainer.addToCart(Integer.parseInt(product.id));
              //  Toast.makeText(getApplicationContext(),DataContainer.cart.size()+"",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

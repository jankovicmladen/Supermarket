package supermarket.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class LoginActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView, mLogoImage, mAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniComponents();

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mImageView.setVisibility(View.GONE);
                    mLogoImage.setVisibility(View.VISIBLE);
                    mAddImage.setVisibility(View.GONE);
                } else {
                    mImageView.setVisibility(View.VISIBLE);
                    mLogoImage.setVisibility(View.GONE);
                    mAddImage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void iniComponents() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new LoginPagerAdapter(getSupportFragmentManager()));

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

        mImageView = (ImageView) findViewById(R.id.image);

        Glide.with(this).load(R.drawable.chrysanthemum)
                .bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                .into(mImageView);

        mLogoImage = (ImageView) findViewById(R.id.img_logo);
        mAddImage = (ImageView) findViewById(R.id.add_image);
    }
}

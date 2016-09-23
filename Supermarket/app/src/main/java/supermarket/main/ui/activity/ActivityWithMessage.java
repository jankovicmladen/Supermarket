package supermarket.main.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.Vector;

import supermarket.main.R;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;


public class ActivityWithMessage extends AppCompatActivity {

    private View mMessageView;
    private TextView mTvMessage;
    private LayoutInflater mInflater;
    private Object busEventListener;
    private ViewGroup viewGroup;
    private Animation animation, animation2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInflater = LayoutInflater.from(getApplicationContext());
//        mMessageView = mInflater.inflate(R.layout.layout_message, null, false);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.show_error_anim);


        busEventListener = new Object() {
            @Subscribe
            public void onMessageShow(MessageObject messageObject) {
                if (mMessageView == null) {
                    mMessageView = mInflater.inflate(R.layout.layout_message, viewGroup, false);
                    mTvMessage = (TextView) mMessageView.findViewById(R.id.text);
                }


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.show_error_anim);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hide_error_message);
                mMessageView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        disableEnableControls(false,viewGroup);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mTvMessage.setText(messageObject.stringResource);


//                boolean tmp = false;
//                for(int i =0; i<viewGroup.getChildCount();i++){
//                    if(viewGroup.getChildAt(i).getId() == mMessageView.getId()){
//                        tmp = true;
//                    }
//                }

                //  if(!tmp){
                viewGroup.addView(mMessageView);
                //   }

                viewGroup.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        mMessageView.startAnimation(animation2);
                        animation2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                viewGroup.removeView(mMessageView);
                                disableEnableControls(true,viewGroup);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        //mMessageView.setVisibility(View.GONE);
                    }
                }, messageObject.time);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewGroup == null) {
            viewGroup = (ViewGroup) findViewById(R.id.rootView);
        }
        BusProvider.getInstance().register(busEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(busEventListener);
    }
    private void disableEnableControls(boolean enable, ViewGroup vg){
        for (int i = 0; i < vg.getChildCount(); i++){
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup){
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }

}

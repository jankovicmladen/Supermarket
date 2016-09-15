package supermarket.main.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.ui.activity.ForgotPasswordActivity;


public class LoginFragment extends Fragment {


    private TextViewFont mTvForgotPassword;
    private View mRootView;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniComponents();
        addListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);
        return mRootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void iniComponents() {
        mTvForgotPassword = (TextViewFont) mRootView.findViewById(R.id.zaboravljena_lozinka);
    }

    private void addListeners(){
        mTvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), ForgotPasswordActivity.class));
            }
        });
    }
}

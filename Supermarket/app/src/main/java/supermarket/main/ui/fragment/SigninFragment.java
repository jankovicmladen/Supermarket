package supermarket.main.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import supermarket.main.R;


public class SigninFragment extends Fragment {

    SwitchCompat mSwIsCompany;
    View mView;
    RelativeLayout mPIB, mCompanyName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_signin, container, false);

        iniComponents();

        addListeners();

        

        return mView;
    }


    private void iniComponents(){
        mSwIsCompany = (SwitchCompat) mView.findViewById(R.id.isCompany);

        mPIB = (RelativeLayout) mView.findViewById(R.id.pib);
        mCompanyName = (RelativeLayout) mView.findViewById(R.id.company_name);

    }

    private void addListeners(){
        mSwIsCompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mCompanyName.setVisibility(View.VISIBLE);
                    mPIB.setVisibility(View.VISIBLE);
                }else{
                    mCompanyName.setVisibility(View.GONE);
                    mPIB.setVisibility(View.GONE);
                }
            }
        });
    }
}

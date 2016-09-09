package supermarket.main;

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



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

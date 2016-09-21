package supermarket.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.EditTextFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataUser;


public class SigninFragment extends Fragment {

    SwitchCompat mSwIsCompany;
    View mView;
    RelativeLayout mPIB, mCompanyName;
    private Spinner mSpinnerCity;

    private EditTextFont mEtUsser;
    private EditTextFont mEtSurname;
    private EditTextFont mEtPassword;
    private EditTextFont mEtconfirmPassword;
    private EditTextFont mEtEmail;
    private EditTextFont mEtPhone;
    private EditTextFont mEtCellPhone;
    private EditTextFont mEtFax;
    private EditTextFont mEtStreet;
    private EditTextFont mEtPib;
    private EditTextFont mEtFirm;
    private EditTextFont mEtGender;
    private EditTextFont mEtBirth;
    private EditTextFont mEtPostCode;
    private EditTextFont mEtCity;
    private EditTextFont mEtEntrency;
    private EditTextFont mEtFlor;
    private EditTextFont mEtAppartment;
    private EditTextFont mEtNumber;
    private Switch switchLegacy;
    private Button buutonContiune;
    private CheckBox mCbNewsletter;

    private DataUser user;

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


    private void iniComponents() {
        mSpinnerCity = (Spinner) mView.findViewById(R.id.spinner_city);

//        ArrayAdapter<String> ciyAdapter = new ArrayAdapter(getActivity().getApplicationContext(),
//                android.R.layout.simple_spinner_item, DataContainer.cityToString(DataContainer.cities));
//
//        ciyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerAdapter ciyAdapter = new supermarket.main.adapters.SpinnerAdapter(getActivity().getApplicationContext(),
                R.layout.simple_spinner_item,DataContainer.cities);
        mSpinnerCity.setAdapter(ciyAdapter);

        mSwIsCompany = (SwitchCompat) mView.findViewById(R.id.isCompany);

        mPIB = (RelativeLayout) mView.findViewById(R.id.pib);
        mCompanyName = (RelativeLayout) mView.findViewById(R.id.company_name);

        mEtUsser = (EditTextFont) mView.findViewById(R.id.first_name);
        mEtSurname = (EditTextFont) mView.findViewById(R.id.last_name);
        mEtPassword = (EditTextFont) mView.findViewById(R.id.password2);
        mEtconfirmPassword = (EditTextFont) mView.findViewById(R.id.password_retype);
        mEtEmail = (EditTextFont) mView.findViewById(R.id.email);
        mEtPhone = (EditTextFont) mView.findViewById(R.id.phone);
        mEtCellPhone = (EditTextFont) mView.findViewById(R.id.cell_phone);
        mEtFax = (EditTextFont) mView.findViewById(R.id.fax);
        mEtStreet = (EditTextFont) mView.findViewById(R.id.street);
        mEtNumber = (EditTextFont) mView.findViewById(R.id.number);
        mEtAppartment = (EditTextFont) mView.findViewById(R.id.appartment);
        mEtFlor = (EditTextFont) mView.findViewById(R.id.floor);
        mEtEntrency = (EditTextFont) mView.findViewById(R.id.entrance);
        mEtCity = (EditTextFont) mView.findViewById(R.id.city);
        mEtPostCode = (EditTextFont) mView.findViewById(R.id.postal_code);
        mEtBirth = (EditTextFont) mView.findViewById(R.id.day);
        // mEtGender=(EditTextFont)mView.findViewById(R.id.textViewGender);
        //mEtFirm=(EditTextFont)mView.findViewById(R.id.textViewFirm);
        // mEtPib=(EditTextFont)mView.findViewById(R.id.textViewPib);


        //mCbNewsletter=(CheckBox)view.findViewById(R.id.checkBox);

        //switchLegacy=(Switch)view.findViewById(R.id.switchLegacy);

        buutonContiune = (Button) mView.findViewById(R.id.btn_nastavi);


    }

    private void addListeners() {
        mSwIsCompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCompanyName.setVisibility(View.VISIBLE);
                    mPIB.setVisibility(View.VISIBLE);
                } else {
                    mCompanyName.setVisibility(View.GONE);
                    mPIB.setVisibility(View.GONE);
                }
            }
        });

        buutonContiune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(mEtEmail.getText().toString())) {
                    Toast.makeText(getActivity().getApplicationContext(), "Netacan email", Toast.LENGTH_SHORT).show();
                } else {

                    String newsletter = "no";
                    if (mCbNewsletter.isChecked()) {
                        newsletter = "yes";
                    }

                    user = new DataUser();

                    final String pass = mEtconfirmPassword.getText().toString();
                    user.name = mEtUsser.getText().toString();
                    user.surname = mEtSurname.getText().toString();
                    user.password = mEtPassword.getText().toString();
                    user.email = mEtEmail.getText().toString();
                    user.phone = mEtPhone.getText().toString();
                    user.cellphone = mEtCellPhone.getText().toString();
                    user.fax = mEtFax.getText().toString();
                    user.street = mEtStreet.getText().toString();
                    user.number = mEtNumber.getText().toString();
                    user.flor = mEtFlor.getText().toString();
                    user.entrecy = mEtEntrency.getText().toString();
                    user.city = mEtCity.getText().toString();
                    user.appartment = mEtAppartment.getText().toString();
                    user.postalcode = mEtPostCode.getText().toString();
                    user.birth = mEtBirth.getText().toString();
                    user.gender = mEtGender.getText().toString();
                    user.firm = mEtPostCode.getText().toString();
                    user.pib = mEtBirth.getText().toString();
                    user.newsletter = newsletter;

                    //  LoginActivity.mViewPager.setCurrentItem(0);
                    postNewUser(getActivity().getApplicationContext(), user, DataContainer.TOKEN);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static void postNewUser(final Context context, final DataUser user, final String token) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, Constant.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, "radi" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", "Mladen");
                params.put("last_name", "Jankovic");
                params.put("email", "mladen.ns@gmail.com");
                params.put("password", "mladen");
                params.put("password_retype", "mladen");
                params.put("cell_phone", "32423423");
                params.put("phone", "435345353");
                params.put("fax", "324234324");
                params.put("street", "Starog kapetana ww");
                params.put("number", "1");
                params.put("apartment", "1");
                params.put("floor", "1");
                params.put("entrency", "1");
                params.put("city", "Beograd");
                params.put("postal_code", "" + 11250);
                params.put("newsletter", "" + 0);
                params.put("day", "" + 15);
                params.put("year", "" + 1993);
                params.put("month", "" + 1);
                params.put("gender", "" + 1);
                params.put("user_type", "buyer");
                params.put("company_name", "");
                params.put("pib", "");
                params.put("token", token);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
}

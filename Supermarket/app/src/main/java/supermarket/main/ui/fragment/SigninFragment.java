package supermarket.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
    View mView;

    SwitchCompat mSwIsCompany;
    RelativeLayout mPIB, mCompanyName;
    private Spinner mSpinnerCity;
    private EditTextFont mEtUsser;
    private EditTextFont mEtSurname;
    private EditTextFont mEtPassword;
    private EditTextFont mEtConfirmPassword;
    private EditTextFont mEtEmail;
    private EditTextFont mEtPhone;
    private EditTextFont mEtCellPhone;
    private EditTextFont mEtFax;
    private EditTextFont mEtStreet;
    private EditTextFont mEtPib;
    private EditTextFont mEtCompanyName;
    private RadioButton mRbMale;
    private RadioButton mRbFemale;
    private EditTextFont mEtBirthday_day, mEtBirthday_month, mEtBirthday_year;
    private EditTextFont mEtPostCode;
    private EditTextFont mEtEntrency;
    private EditTextFont mEtFlor;
    private EditTextFont mEtAppartment;
    private EditTextFont mEtNumber;
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

        SpinnerAdapter ciyAdapter = new supermarket.main.adapters.SpinnerAdapter(getActivity().getApplicationContext(),
                R.layout.simple_spinner_item, DataContainer.cities);
        mSpinnerCity.setAdapter(ciyAdapter);

        mSwIsCompany = (SwitchCompat) mView.findViewById(R.id.isCompany);

        mPIB = (RelativeLayout) mView.findViewById(R.id.pib);
        mCompanyName = (RelativeLayout) mView.findViewById(R.id.company_name);

        mEtUsser = (EditTextFont) mView.findViewById(R.id.first_name);
        mEtSurname = (EditTextFont) mView.findViewById(R.id.last_name);
        mEtPassword = (EditTextFont) mView.findViewById(R.id.password2);
        mEtConfirmPassword = (EditTextFont) mView.findViewById(R.id.password_retype);
        mEtEmail = (EditTextFont) mView.findViewById(R.id.email);
        mEtPhone = (EditTextFont) mView.findViewById(R.id.phone);
        mEtCellPhone = (EditTextFont) mView.findViewById(R.id.cell_phone);
        mEtFax = (EditTextFont) mView.findViewById(R.id.fax);
        mEtStreet = (EditTextFont) mView.findViewById(R.id.street);
        mEtNumber = (EditTextFont) mView.findViewById(R.id.number);
        mEtAppartment = (EditTextFont) mView.findViewById(R.id.appartment);
        mEtFlor = (EditTextFont) mView.findViewById(R.id.floor);
        mEtEntrency = (EditTextFont) mView.findViewById(R.id.entrance);

        mEtPostCode = (EditTextFont) mView.findViewById(R.id.postal_code);
        mEtBirthday_day = (EditTextFont) mView.findViewById(R.id.day);
        mEtBirthday_month = (EditTextFont) mView.findViewById(R.id.month);
        mEtBirthday_year = (EditTextFont) mView.findViewById(R.id.year);
        mRbMale = (RadioButton) mView.findViewById(R.id.male);
        mRbFemale = (RadioButton) mView.findViewById(R.id.female);
        mEtCompanyName = (EditTextFont) mView.findViewById(R.id.et_company_name);
        mEtPib = (EditTextFont) mView.findViewById(R.id.et_pib);
        mCbNewsletter = (CheckBox)mView.findViewById(R.id.newsletter);
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
//                if (isValidEmail(mEtEmail.getText().toString())) {
//                    Toast.makeText(getActivity().getApplicationContext(), "Netacan email", Toast.LENGTH_SHORT).show();
//                } else {

                    String newsletter = "no";
                    if (mCbNewsletter.isChecked()) {
                        newsletter = "yes";
                    }

                    user = new DataUser();

                    final String pass = mEtConfirmPassword.getText().toString();
                    user.name = mEtUsser.getText().toString();
                    user.surname = mEtSurname.getText().toString();
                    user.password = mEtPassword.getText().toString();
                    user.passworeRetype = mEtConfirmPassword.getText().toString();
                    user.email = mEtEmail.getText().toString();
                    user.phone = mEtPhone.getText().toString();
                    user.cellphone = mEtCellPhone.getText().toString();
                    user.fax = mEtFax.getText().toString();
                    user.street = mEtStreet.getText().toString();
                    user.number = mEtNumber.getText().toString();
                    user.floor = mEtFlor.getText().toString();
                    user.entrance = mEtEntrency.getText().toString();

                    user.city = mSpinnerCity.getSelectedItem().toString();

                    user.appartment = mEtAppartment.getText().toString();
                    user.postalcode = mEtPostCode.getText().toString();
                    user.birthday_day = mEtBirthday_day.getText().toString();
                    user.birthday_month = mEtBirthday_month.getText().toString();
                    user.birthday_year = mEtBirthday_year.getText().toString();

                    if (mRbMale.isChecked()) {
                        user.gender = "1";
                    } else if (mRbFemale.isChecked()) {
                        user.gender = "2";
                    }

                    if(mSwIsCompany.isChecked()){
                        user.user_type = "company";
                    }else{
                        user.user_type = "buyer";
                    }

                    user.company_name= mEtCompanyName.getText().toString();
                    user.pib = mEtPib.getText().toString();

                    if (mCbNewsletter.isChecked()){
                        user.newsletter = "1";
                    }else {
                        user.newsletter = "0";
                    }

                   postNewUser(getActivity().getApplicationContext(), user, DataContainer.TOKEN);

                }
          //  }
        });
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static void postNewUser(final Context context,final DataUser user, final String token) {

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST, Constant.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("odgovor",response);
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
                params.put("first_name", user.name);
                params.put("last_name", user.surname);
                params.put("email", user.email);
                params.put("password", user.password);
                params.put("password_retype", user.passworeRetype);
                params.put("cell_phone", user.cellphone);
                params.put("phone", user.phone);
                params.put("fax", user.fax);
                params.put("street", user.street);
                params.put("number", user.number);
                params.put("apartment", user.appartment);
                params.put("floor", user.floor);
                params.put("entrance", user.entrance);
                params.put("city", user.city);
                params.put("postal_code", user.postalcode);
                params.put("newsletter", user.newsletter);
                params.put("day", user.birthday_day);
                params.put("year", user.birthday_month);
                params.put("month", user.birthday_year);
                params.put("gender", user.gender);
                params.put("user_type", user.user_type);
                params.put("company_name", user.company_name);
                params.put("pib", user.pib);
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

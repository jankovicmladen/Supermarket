package supermarket.main.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import supermarket.main.R;
import supermarket.main.customComponents.EditTextFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataUser;

public class ProfilActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        iniComponents();

    }

    private void iniComponents() {
        mSpinnerCity = (Spinner) findViewById(R.id.spinner_city);

        SpinnerAdapter ciyAdapter = new supermarket.main.adapters.SpinnerAdapter(getApplicationContext(),
                R.layout.simple_spinner_item, DataContainer.cities);
        mSpinnerCity.setAdapter(ciyAdapter);

        mSwIsCompany = (SwitchCompat) findViewById(R.id.isCompany);

        mPIB = (RelativeLayout) findViewById(R.id.pib);
        mCompanyName = (RelativeLayout) findViewById(R.id.company_name);

        mEtUsser = (EditTextFont) findViewById(R.id.first_name);
        mEtSurname = (EditTextFont) findViewById(R.id.last_name);
        mEtEmail = (EditTextFont) findViewById(R.id.email);
        mEtPhone = (EditTextFont) findViewById(R.id.phone);
        mEtCellPhone = (EditTextFont) findViewById(R.id.cell_phone);
        mEtFax = (EditTextFont) findViewById(R.id.fax);
        mEtStreet = (EditTextFont) findViewById(R.id.street);
        mEtNumber = (EditTextFont) findViewById(R.id.number);
        mEtAppartment = (EditTextFont) findViewById(R.id.appartment);
        mEtFlor = (EditTextFont) findViewById(R.id.floor);
        mEtEntrency = (EditTextFont) findViewById(R.id.entrance);

        mEtPostCode = (EditTextFont) findViewById(R.id.postal_code);
        mEtBirthday_day = (EditTextFont) findViewById(R.id.day);
        mEtBirthday_month = (EditTextFont) findViewById(R.id.month);
        mEtBirthday_year = (EditTextFont) findViewById(R.id.year);
        mRbMale = (RadioButton) findViewById(R.id.male);
        mRbFemale = (RadioButton) findViewById(R.id.female);
        mEtCompanyName = (EditTextFont) findViewById(R.id.et_company_name);
        mEtPib = (EditTextFont) findViewById(R.id.et_pib);
        mCbNewsletter = (CheckBox) findViewById(R.id.newsletter);
        buutonContiune = (Button) findViewById(R.id.btn_nastavi);


    }

}

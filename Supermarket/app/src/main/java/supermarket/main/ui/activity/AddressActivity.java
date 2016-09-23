package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import supermarket.main.R;
import supermarket.main.adapters.SpinnerAdapter;
import supermarket.main.customComponents.EditTextFont;
import supermarket.main.data.container.DataContainer;

public class AddressActivity extends AppCompatActivity implements Serializable {

    private static final int REQUEST_EXIT = 1;
    private EditTextFont mEtStreet, mEtNumber, mEtApartment, mEtFloor;
    private EditTextFont mEtEntrance, mEtCity, mEtPostalCode;
    private Button mBtnVerifyAddress;
    private Spinner mSpNacinPlacanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        iniComponents();
        addListeners();
        addAdapterToSpinner();
    }

    private void addListeners() {
        mBtnVerifyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataContainer.user.address = mEtStreet.getText().toString();
                DataContainer.user.street_number = mEtNumber.getText().toString();
                DataContainer.user.appartment = mEtApartment.getText().toString();
                DataContainer.user.floor = mEtFloor.getText().toString();
                DataContainer.user.entrance = mEtEntrance.getText().toString();
                DataContainer.user.city = mEtCity.getText().toString();
                DataContainer.user.postalcode = mEtPostalCode.getText().toString();

                startActivityForResult(new Intent(getApplicationContext(), BuyingActivity.class), REQUEST_EXIT);
            }
        });
    }

    private void iniComponents() {
        mEtStreet = (EditTextFont) findViewById(R.id.street);
        mEtStreet.setText(DataContainer.user.address);

        mEtNumber = (EditTextFont) findViewById(R.id.number);
        mEtNumber.setText(DataContainer.user.street_number);

        mEtApartment = (EditTextFont) findViewById(R.id.appartment);
        mEtApartment.setText(DataContainer.user.appartment);

        mEtFloor = (EditTextFont) findViewById(R.id.floor);
        mEtFloor.setText(DataContainer.user.floor);

        mEtEntrance = (EditTextFont) findViewById(R.id.entrance);
        mEtEntrance.setText(DataContainer.user.entrance);

        mEtCity = (EditTextFont) findViewById(R.id.city);
        mEtCity.setText(DataContainer.user.city);

        mEtPostalCode = (EditTextFont) findViewById(R.id.postal_code);
        mEtPostalCode.setText(DataContainer.user.postalcode);

        mBtnVerifyAddress = (Button) findViewById(R.id.btn_verify_address);

        mSpNacinPlacanja = (Spinner) findViewById(R.id.spinner_nacin_placanja);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();

            }
        }
    }

    private void addAdapterToSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nacin_kupovine, R.layout.item_nacin_placanja);

        adapter.setDropDownViewResource(R.layout.item_nacin_placanja2);
        mSpNacinPlacanja.setAdapter(adapter);

    }
}

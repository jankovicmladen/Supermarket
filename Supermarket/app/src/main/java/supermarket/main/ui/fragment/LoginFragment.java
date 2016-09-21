package supermarket.main.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.EditTextFont;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.response.ResponseProducts;
import supermarket.main.data.response.ResponseUser;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonReguest;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;
import supermarket.main.ui.activity.ForgotPasswordActivity;
import supermarket.main.ui.activity.HomeActivity;


public class LoginFragment extends Fragment {

    private final String REQUEST_TAG = "Login";
    public static final String PREFS_NAME = "login";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String STAY_LOGIN = "staylogin";

    private TextViewFont mTvForgotPassword;
    private View mRootView;

    private EditTextFont mEtUsername;
    private EditTextFont mEtPassword;

    private CheckBox mCbStayLogin;

    private Button mBtnLogin;

    private GsonReguest<ResponseProducts> responseProducts;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);

        iniComponents();
        addListeners();

        return mRootView;
    }


    private void iniComponents() {
        mTvForgotPassword = (TextViewFont) mRootView.findViewById(R.id.zaboravljena_lozinka);

        mEtUsername = (EditTextFont) mRootView.findViewById(R.id.et_username);
        mEtPassword = (EditTextFont) mRootView.findViewById(R.id.et_password);
        mCbStayLogin = (CheckBox) mRootView.findViewById(R.id.staylogin);

        mBtnLogin = (Button) mRootView.findViewById(R.id.btn_nastavi);
    }

    private void addListeners() {
        mTvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), ForgotPasswordActivity.class));
            }
        });

        responseProducts = new GsonReguest<ResponseProducts>(
                Constant.PRODUCTS, Request.Method.GET, ResponseProducts.class,
                new Response.Listener<ResponseProducts>() {
                    @Override
                    public void onResponse(ResponseProducts response) {
                        Log.i("response", response.data.status);

                        DataContainer.products = response.data.results;
                        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                        getActivity().finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GsonReguest<ResponseUser> gsonReguest = new GsonReguest<ResponseUser>(Constant.LOGIN_URL,
                        Request.Method.POST, ResponseUser.class,
                        new Response.Listener<ResponseUser>() {
                            @Override
                            public void onResponse(ResponseUser response) {
                                DataContainer.user = response.data.results;
                                DataContainer.token = response.data.token;
                                DataContainer.loginToken = response.data.login_token;
                                Log.i("token", DataContainer.loginToken);

                                try {
                                    String encryptedMsg = AESCrypt.encrypt(Constant.PASSWORD, mEtPassword.getText().toString());
                                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor.putString(USERNAME, mEtUsername.getText().toString());
                                    editor.putString(PASSWORD, encryptedMsg);
                                    if (mCbStayLogin.isChecked()) {
                                        editor.putBoolean(STAY_LOGIN, true);
                                    }else {
                                        editor.putBoolean(STAY_LOGIN,false);
                                    }

                                    // Commit the edits!
                                    editor.commit();

                                } catch (GeneralSecurityException e) {
                                    //handle error
                                }

                                DataLoader.addRequest(getActivity().getApplicationContext(), responseProducts, REQUEST_TAG);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<>();

                        params.put("email", mEtUsername.getText().toString());
                        params.put("password", mEtPassword.getText().toString());
                        params.put("token", DataContainer.TOKEN);

                        return params;
                    }
                };

                DataLoader.addRequest(getActivity().getApplicationContext(), gsonReguest, REQUEST_TAG);
            }
        });
    }

}

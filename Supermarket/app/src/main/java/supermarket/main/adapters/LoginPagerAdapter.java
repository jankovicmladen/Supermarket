package supermarket.main.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import supermarket.main.ui.fragment.LoginFragment;
import supermarket.main.ui.fragment.SigninFragment;

/**
 * Created by cubesschool2 on 9/7/16.
 */
public class LoginPagerAdapter extends FragmentPagerAdapter {

    private String tabs[] = {"PRIJAVA", "REGISTRACIJA"};
    private Context context;

    public LoginPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new LoginFragment();
        }else
            return new SigninFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}

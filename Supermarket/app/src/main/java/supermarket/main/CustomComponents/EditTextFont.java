package supermarket.main.customComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import supermarket.main.R;

/**
 * Created by Mladen on 9/7/2016.
 */
public class EditTextFont extends EditText {
    public EditTextFont(Context context) {
        super(context);
        setHintTextColor(this.getResources().getColor(R.color.white));
    }

    public EditTextFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHintTextColor(this.getResources().getColor(R.color.white));
    }

    public EditTextFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHintTextColor(this.getResources().getColor(R.color.white));
    }

}

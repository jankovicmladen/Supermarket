package supermarket.main.tool;

import android.view.ViewGroup;

import supermarket.main.R;

/**
 * Created by cubesschool2 on 9/14/16.
 */
public class MessageObject {

    public static final int MESSAGE_ERROR = 0;
    public static final int MESSAGE_SUCCESS = 1;
    public static final int MESSAGE_INFO = 2;

    public int stringResource;
    public int time;
    public int type;
    public OnMessageClickListener listener;


    public interface OnMessageClickListener{
        void onClick();
    }
    public MessageObject(int stringResource, int time, int type, OnMessageClickListener listener) {
        this.stringResource = stringResource;
        this.time = time;
        this.type = type;
        this.listener = listener;
    }
    public MessageObject() {
        this.stringResource = R.string.error_internet;
        this.time = 5000;
        this.type = MESSAGE_ERROR;
        this.listener = null;
    }


}

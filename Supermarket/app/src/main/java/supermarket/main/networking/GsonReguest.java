package supermarket.main.networking;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by cubesschool2 on 9/12/16.
 */
public class GsonReguest<T> extends Request<T> {

    private final int TIMEOUT = 10000;

    private Gson mGson = new Gson();
    private Class<T> mClass;
    private Response.Listener<T> mListener;
    private Response.ErrorListener mErrorListener;

    public GsonReguest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public GsonReguest(String url, int type, Class<T> tClass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(type, url, errorListener);

        this.mClass = tClass;
        this.mListener = listener;
        this.mErrorListener = errorListener;

        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //definisemo posle koliko vremena da nam javi gresku ukoliko ne dodje do requesta,
        // drugo polje koliko puta da pokusava dok ne dobijemo error

    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {

        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers)); //daje string json od responsa, response data - podaic, drugi parametar je charset koji je def u responsu

            return Response.success(mGson.fromJson(json, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }
}

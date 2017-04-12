package pt.rht;

import android.app.Application;
import android.os.SystemClock;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Kitsao on 20/03/2017.
 */

public class RhtPT extends Application {
    public static final String TAG = RhtPT.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static RhtPT mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SystemClock.sleep(2000);
    }
    public static synchronized RhtPT getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

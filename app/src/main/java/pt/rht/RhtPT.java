package pt.rht;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by ilabafrica on 20/03/2017.
 */

public class RhtPT extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}

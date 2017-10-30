package net.atomation.buli;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by noaraz on 03/10/2017.
 */


public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    private static App sInstance;

    public static Context getAppContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called. initializing app context...");
        sInstance = this;


    }
}

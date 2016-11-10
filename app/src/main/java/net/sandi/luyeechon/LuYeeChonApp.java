package net.sandi.luyeechon;

import android.app.Application;
import android.content.Context;

/**
 * Created by UNiQUE on 9/18/2016.
 */
public class LuYeeChonApp extends Application {

    public static final String TAG="LuYeeChonApp";

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}

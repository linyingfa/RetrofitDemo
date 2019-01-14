package y530.retrofitdemo;

import android.app.Application;
import android.content.Context;

import y530.retrofitdemo.network.AppUtils;
import y530.retrofitdemo.network2.utils.Utils;

public class App extends Application {

    public static Context appContext;
    public static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppUtils.init(app);
        Utils.init(this);
    }


    public static App getApp() {
        return (App) appContext;
    }


}

package test.bawei.com.application;

import android.app.Application;

import test.bawei.com.utils.ImageLoderUtils;

/**
 * Created by lenovo-pc on 2016/12/22.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoderUtils.initConfiguration(getApplicationContext());
    }
}

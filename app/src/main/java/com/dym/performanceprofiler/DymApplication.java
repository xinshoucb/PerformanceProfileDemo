package com.dym.performanceprofiler;

import android.app.Application;
import android.util.Log;

import com.dym.performanceprofiler.libs.block.BlockTrace;

/**
 * DymApplication class
 *
 * @author hangwei
 * @date 2018/12/7
 */
public class DymApplication extends Application {

    public static final String TAG = "DymApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        BlockTrace.start();
    }

    @Override
    public void onTerminate() {
        Log.d(TAG,"onTerminate");
        super.onTerminate();
    }
}

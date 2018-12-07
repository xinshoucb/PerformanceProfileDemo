package com.dym.performanceprofiler.libs.block;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * BlockMonitor class
 *
 * @author hangwei
 * @date 2018/12/7
 */
public class BlockMonitor {
    public static final String TAG = "BlockMonitor";
    private static volatile BlockMonitor sInstance;
    private Handler mIoHandler;
    private HandlerThread mLogThread;
    private static final long TIME_BLOCK = 100L;

    private BlockMonitor() {
        mLogThread = new HandlerThread("BlockMonitor");
        mLogThread.start();
        mIoHandler = new Handler(mLogThread.getLooper());
    }


    public static BlockMonitor getInstance() {
        if (sInstance == null) {
            synchronized (BlockMonitor.class){
                if (sInstance == null) {
                    sInstance = new BlockMonitor();
                }
            }
        }

        return sInstance;
    }

    public void startMonitor() {
        mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
    }

    public void removeMonitor() {
        mIoHandler.removeCallbacks(mLogRunnable);
    }

    public void release(){
        synchronized (BlockMonitor.class){
            sInstance = null;
        }

        if (mLogThread != null) {
            mLogThread.quit();
            mLogThread = null;
        }

        if (mIoHandler != null) {
            mIoHandler.removeCallbacksAndMessages(null);
            mIoHandler = null;
        }
    }

    private static Runnable mLogRunnable = new Runnable() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            for (StackTraceElement s : stackTrace) {
                sb.append(s.toString() + "\n");
            }
            Log.e(TAG, sb.toString());
        }
    };
}

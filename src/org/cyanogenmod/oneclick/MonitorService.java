package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * Created by koush on 10/3/13.
 */
abstract public class MonitorService extends Service {
    private Handler mHandler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    private void cleanupAndShutdown() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        stopSelf();
    }

    protected abstract Class<? extends Activity> getNextActivityClass();

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new Handler();

        new Runnable() {
            @Override
            public void run() {
                if (mHandler == null)
                    return;
                if (canContinue()) {
                    Intent i = new Intent(getBaseContext(), getNextActivityClass());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                    cleanupAndShutdown();

                    return;
                }
                mHandler.postDelayed(this, 1000);
            }
        }.run();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cleanupAndShutdown();
            }
        }, 60000);
    }

    protected abstract boolean canContinue();
}

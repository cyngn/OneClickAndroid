/*
 * Copyright 2014 Cyanogen, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public abstract class MonitorService extends Service {
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

        (new Runnable() {
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
        }).run();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cleanupAndShutdown();
            }
        }, 60000);
    }

    protected abstract boolean canContinue();
}

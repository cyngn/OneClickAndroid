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
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

public class UnplugDeviceActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!deviceIsPluggedIn()) {
            startActivity(new Intent(getBaseContext(), FinishActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.unplugdevice);

        startService(new Intent(getBaseContext(), UnplugDeviceMonitorService.class));
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!deviceIsPluggedIn()) {
            finish();
        }
    }

    private boolean deviceIsPluggedIn() {
        Intent intent = getBaseContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return (plugged == BatteryManager.BATTERY_PLUGGED_USB) || (plugged == BatteryManager.BATTERY_PLUGGED_AC);
    }
}

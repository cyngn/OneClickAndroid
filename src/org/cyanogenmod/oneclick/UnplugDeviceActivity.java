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
            Analytics.send(Analytics.DEVICE_ALREADY_UNPLUGGED);
            startActivity(new Intent(getBaseContext(), FinishActivity.class));
            finish();
            return;
        }

        Analytics.send(Analytics.UNPLUG_DEVICE);
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

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class UnplugDeviceMonitorService extends MonitorService {
    @Override
    protected boolean canContinue() {
        Intent intent = getBaseContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean isPlugged = (plugged == BatteryManager.BATTERY_PLUGGED_USB) || (plugged == BatteryManager.BATTERY_PLUGGED_AC);
        return !isPlugged;
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return FinishActivity.class;
    }
}

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.ComponentName;
import android.provider.Settings;

/**
 * Created by koush on 10/3/13.
 */
public class HTCFastBootMonitorService extends MonitorService {
    ComponentName htcPowerManager = new ComponentName("com.htc.htcpowermanager", "com.htc.htcpowermanager.PowerManagerActivity");
    @Override
    protected boolean canContinue() {
        try {
            getPackageManager().getActivityInfo(htcPowerManager, 0);
        }
        catch (Exception e) {
            return true;
        }

        return Settings.Secure.getInt(getContentResolver(), "enable_fastboot", 1) == 0;
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return UnplugDeviceActivity.class;
    }
}

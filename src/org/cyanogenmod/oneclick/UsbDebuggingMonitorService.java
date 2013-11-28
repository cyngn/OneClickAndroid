package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.provider.Settings;

public class UsbDebuggingMonitorService extends MonitorService {
    @SuppressWarnings("deprecation")
    @Override
    protected boolean canContinue() {
        return (Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1);
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return PtpActivity.class;
    }
}

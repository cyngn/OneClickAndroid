package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.os.SystemProperties;

public class PtpMonitorService extends MonitorService {
    @Override
    protected boolean canContinue() {
        String usbConfig = SystemProperties.get("persist.sys.usb.config");
        if (usbConfig != null && usbConfig.contains("mtp")) {
            return false;
        }
        return true;
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return HTCFastBootActivity.class;
    }
}

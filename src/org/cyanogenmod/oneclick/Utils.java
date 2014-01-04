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

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.SystemProperties;
import android.provider.Settings;

import java.util.ArrayList;

public class Utils {
    private static final ArrayList<String> SUPPORTED_DEVICES = new ArrayList<String>();

    static {
        SUPPORTED_DEVICES.add("blaze4g"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("crespo");
        SUPPORTED_DEVICES.add("crespo4g");
        SUPPORTED_DEVICES.add("d2att");
        SUPPORTED_DEVICES.add("d2can");
        SUPPORTED_DEVICES.add("d2cri");
        SUPPORTED_DEVICES.add("d2spr");
        SUPPORTED_DEVICES.add("d2tmo");
        SUPPORTED_DEVICES.add("d2usc");
        SUPPORTED_DEVICES.add("deb");
        SUPPORTED_DEVICES.add("find5");
        SUPPORTED_DEVICES.add("flo");
        SUPPORTED_DEVICES.add("galaxynote");
        SUPPORTED_DEVICES.add("galaxys2");
        SUPPORTED_DEVICES.add("grouper");
        SUPPORTED_DEVICES.add("gt-i9100"); // Alternative for 'i9100'
        SUPPORTED_DEVICES.add("gt-i9100m"); // Alternative for 'i9100'
        SUPPORTED_DEVICES.add("gt-i9100p"); // Alternative for 'i9100'
        SUPPORTED_DEVICES.add("gt-i9100t"); // Alternative for 'i9100'
        SUPPORTED_DEVICES.add("gt-i9300"); // Alternative for 'i9300'
        SUPPORTED_DEVICES.add("gt-i9505");
        SUPPORTED_DEVICES.add("gt-i9505g");
        SUPPORTED_DEVICES.add("gt-n7000"); // Alternative for 'n7000'
        SUPPORTED_DEVICES.add("gt-n7105");
        SUPPORTED_DEVICES.add("gt-n7105t");
        SUPPORTED_DEVICES.add("hammerhead");
        SUPPORTED_DEVICES.add("hercules");
        SUPPORTED_DEVICES.add("i9100");
        SUPPORTED_DEVICES.add("i9300");
        SUPPORTED_DEVICES.add("i9505");
        SUPPORTED_DEVICES.add("i9505g");
        SUPPORTED_DEVICES.add("jflte"); // Alternative for 'jfltexx'
        SUPPORTED_DEVICES.add("jfltecan");
        SUPPORTED_DEVICES.add("jfltecri");
        SUPPORTED_DEVICES.add("jfltecsp");
        SUPPORTED_DEVICES.add("jfltespr");
        SUPPORTED_DEVICES.add("jfltetmo");
        SUPPORTED_DEVICES.add("jflteusc");
        SUPPORTED_DEVICES.add("jfltexx");
        SUPPORTED_DEVICES.add("jgedlte"); // Alternative for 'jfltexx'
        SUPPORTED_DEVICES.add("l900");
        SUPPORTED_DEVICES.add("m0"); // Alternative for 'i9300'
        SUPPORTED_DEVICES.add("m7"); // Alternative for 'm7ul' and other variants.
        SUPPORTED_DEVICES.add("m7_spr"); // Alternative for 'm7spr'
        SUPPORTED_DEVICES.add("m7_ul"); // Alternative for 'm7ul'
        SUPPORTED_DEVICES.add("m7_wls"); // Alternative for 'm7spr'
        SUPPORTED_DEVICES.add("m7att");
        SUPPORTED_DEVICES.add("m7spr");
        SUPPORTED_DEVICES.add("m7tmo");
        SUPPORTED_DEVICES.add("m7ul");
        SUPPORTED_DEVICES.add("m7wls"); // Alternative for 'm7spr'
        SUPPORTED_DEVICES.add("maguro");
        SUPPORTED_DEVICES.add("mako");
        SUPPORTED_DEVICES.add("manta");
        SUPPORTED_DEVICES.add("n1");
        SUPPORTED_DEVICES.add("n7000");
        SUPPORTED_DEVICES.add("n7100");
        SUPPORTED_DEVICES.add("quincyatt");
        SUPPORTED_DEVICES.add("quincytmo");
        SUPPORTED_DEVICES.add("sgh-i317"); // Alternative for 't0lteatt'
        SUPPORTED_DEVICES.add("sgh-i317m"); // Alternative for 't0lteatt'
        SUPPORTED_DEVICES.add("sgh-i717"); // Alternative for 'quincyatt'
        SUPPORTED_DEVICES.add("sgh-i727"); // Alternative for 'skyrocket'
        SUPPORTED_DEVICES.add("sgh-i747"); // Alternative for 'd2att'
        SUPPORTED_DEVICES.add("sgh-t769"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("sgh-t769"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("sgh-t879"); // Alternative for 'quincytmo'
        SUPPORTED_DEVICES.add("sgh-t989"); // Alternative for 'hercules'
        SUPPORTED_DEVICES.add("sgh-t999"); // Alternative for 'd2tmo'
        SUPPORTED_DEVICES.add("sgh-t999v"); // Alternative for 'd2tmo'
        SUPPORTED_DEVICES.add("skyrocket");
        SUPPORTED_DEVICES.add("sph-l710"); // Alternative for 'd2spr'
        SUPPORTED_DEVICES.add("sph-l900"); // Alternative for 'l900'
        SUPPORTED_DEVICES.add("t03g"); // Alternative for 'n7100'
        SUPPORTED_DEVICES.add("t0lte");
        SUPPORTED_DEVICES.add("t0lteatt");
        SUPPORTED_DEVICES.add("t0ltecan");
        SUPPORTED_DEVICES.add("t0ltespr"); // Alternative for 'l900'
        SUPPORTED_DEVICES.add("t0ltetmo");
        SUPPORTED_DEVICES.add("t0ltexx");
        SUPPORTED_DEVICES.add("t769");
        SUPPORTED_DEVICES.add("tilapia");
        SUPPORTED_DEVICES.add("toro");
        SUPPORTED_DEVICES.add("toroplus");
        SUPPORTED_DEVICES.add("tuna"); // Alternative for 'maguro'
        SUPPORTED_DEVICES.add("x909"); // Alternative for 'find5'
    }

    public static boolean isDeviceSupported() {
        String device = SystemProperties.get("ro.product.device");
        if (device != null) {
            if (SUPPORTED_DEVICES.contains(device.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public static boolean ptpIsEnabled() {
        String usbConfig = SystemProperties.get("persist.sys.usb.config");
        if (usbConfig != null && usbConfig.contains("mtp")) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public static boolean adbIsEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1;
    }

    public static boolean fastbootIsDisabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "enable_fastboot", 1) == 0;
    }

    public static boolean deviceIsPluggedIn(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return (plugged == BatteryManager.BATTERY_PLUGGED_USB) || (plugged == BatteryManager.BATTERY_PLUGGED_AC);
    }
}

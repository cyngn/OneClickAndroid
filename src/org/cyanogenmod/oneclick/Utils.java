package org.cyanogenmod.oneclick;

import android.os.SystemProperties;

import java.util.ArrayList;

public class Utils {
    private static final ArrayList<String> SUPPORTED_DEVICES = new ArrayList<String>();

    static {
        SUPPORTED_DEVICES.add("crespo");
        SUPPORTED_DEVICES.add("crespo4g");
        SUPPORTED_DEVICES.add("maguro");
        SUPPORTED_DEVICES.add("tuna"); // Alternative for 'maguro'
        SUPPORTED_DEVICES.add("toro");
        SUPPORTED_DEVICES.add("toroplus");
        SUPPORTED_DEVICES.add("grouper");
        SUPPORTED_DEVICES.add("tilapia");
        SUPPORTED_DEVICES.add("flo");
        SUPPORTED_DEVICES.add("mako");
        SUPPORTED_DEVICES.add("manta");
        SUPPORTED_DEVICES.add("skyrocket");
        SUPPORTED_DEVICES.add("SGH-I727"); // Alternative for 'skyrocket'
        SUPPORTED_DEVICES.add("hercules");
        SUPPORTED_DEVICES.add("SGH-T989"); // Alternative for 'hercules'
        SUPPORTED_DEVICES.add("i9100");
        SUPPORTED_DEVICES.add("GT-I9100"); // Alternative for 'i9100'
        SUPPORTED_DEVICES.add("i9300");
        SUPPORTED_DEVICES.add("GT-I9300"); // Alternative for 'i9300'
        SUPPORTED_DEVICES.add("m0"); // Alternative for 'i9300'
        SUPPORTED_DEVICES.add("d2att");
        SUPPORTED_DEVICES.add("SGH-I747"); // Alternative for 'd2att'
        SUPPORTED_DEVICES.add("d2spr");
        SUPPORTED_DEVICES.add("SPH-L710"); // Alternative for 'd2spr'
        SUPPORTED_DEVICES.add("d2tmo");
        SUPPORTED_DEVICES.add("SGH-T999"); // Alternative for 'd2tmo'
        SUPPORTED_DEVICES.add("SGH-T999V"); // Alternative for 'd2tmo'
        SUPPORTED_DEVICES.add("d2usc");
        SUPPORTED_DEVICES.add("d2cri");
        SUPPORTED_DEVICES.add("t769");
        SUPPORTED_DEVICES.add("SGH-T769"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("sgh-t769"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("Blaze4G"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("Blaze4g"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("blaze4G"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("blaze4g"); // Alternative for 't769'
        SUPPORTED_DEVICES.add("jfltexx");
        SUPPORTED_DEVICES.add("jflte"); // Alternative for 'jfltexx'
        SUPPORTED_DEVICES.add("jgedlte"); // Alternative for 'jfltexx'
        SUPPORTED_DEVICES.add("jfltespr");
        SUPPORTED_DEVICES.add("jfltecan");
        SUPPORTED_DEVICES.add("jfltecri");
        SUPPORTED_DEVICES.add("jfltecsp");
        SUPPORTED_DEVICES.add("jfltetmo");
        SUPPORTED_DEVICES.add("jflteusc");
        SUPPORTED_DEVICES.add("n7000");
        SUPPORTED_DEVICES.add("GT-N7000"); // Alternative for 'n7000'
        SUPPORTED_DEVICES.add("quincyatt");
        SUPPORTED_DEVICES.add("SGH-I717"); // Alternative for 'quincyatt'
        SUPPORTED_DEVICES.add("quincytmo");
        SUPPORTED_DEVICES.add("SGH-T879"); // Alternative for 'quincytmo'
        SUPPORTED_DEVICES.add("t0lte");
        SUPPORTED_DEVICES.add("n7100");
        SUPPORTED_DEVICES.add("t03g"); // Alternative for 'n7100'
        SUPPORTED_DEVICES.add("t0lteatt");
        SUPPORTED_DEVICES.add("SGH-I317"); // Alternative for 't0lteatt'
        SUPPORTED_DEVICES.add("t0ltetmo");
        SUPPORTED_DEVICES.add("l900");
        SUPPORTED_DEVICES.add("SPH-L900"); // Alternative for 'l900'
        SUPPORTED_DEVICES.add("t0ltespr"); // Alternative for 'l900'
        SUPPORTED_DEVICES.add("m7ul");
        SUPPORTED_DEVICES.add("m7"); // Alternative for 'm7ul' and other variants.
        SUPPORTED_DEVICES.add("m7_ul"); // Alternative for 'm7ul'
        SUPPORTED_DEVICES.add("m7tmo");
        SUPPORTED_DEVICES.add("m7_tmo"); // Alternative for 'm7tmo'
        SUPPORTED_DEVICES.add("m7att");
        SUPPORTED_DEVICES.add("m7_att"); // Alternative for 'm7att'
        SUPPORTED_DEVICES.add("m7spr");
        SUPPORTED_DEVICES.add("m7_spr"); // Alternative for 'm7spr'
        SUPPORTED_DEVICES.add("m7_wls"); // Alternative for 'm7spr'
        SUPPORTED_DEVICES.add("m7wls"); // Alternative for 'm7spr'
    }

    public static boolean isDeviceSupported() {
        String device = SystemProperties.get("ro.product.device");
        if (device != null) {
            if (SUPPORTED_DEVICES.contains(device)) {
                return true;
            }
        }

        return false;
    }
}

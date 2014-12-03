package org.cyanogenmod.oneclick;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class OneClickStats {

    public static final class Fields {
        public static final String EVENT_CATEGORY = "category";
        public static final String EVENT_ACTION = "action";
        public static final String EVENT_LABEL = "label";
        public static final String EVENT_VALUE = "value";
    }

    public static final class Categories {
        public static final String PAGE_SHOWN = "page_shown";
        public static final String BUTTON_CLICK = "button_click";
        public static final String SWITCH_ERROR = "switch_error";
    }

    public static final class Actions {
        public static final String PAGE_COMPAT_CHECK = "1.compat_check";
        public static final String PAGE_WELCOME = "2.Welcome";
        public static final String PAGE_DISCLAIMER = "3.Disclaimer";
        public static final String PAGE_ADB = "4.ADB";
        public static final String PAGE_PTP = "5.PTP";
        public static final String PAGE_HTC_FASTBOOT = "6.HTC_Fastboot";
        public static final String PAGE_UNPLUG = "7.Unplug";
        public static final String PAGE_FINISH = "8.Finish";

        public static final String BTN_UNSUPP_CONT = "unsupp_cont";
        public static final String BTN_WELCOME = "Welcome_Begin";
        public static final String BTN_DISCLAIMER = "Disclaimer_Agree";
        public static final String BTN_HTC_FASTBOOT = "HTC_Fastboot_cont";
        public static final String BTN_PTP = "ptp_cont";
        public static final String BTN_ADB = "adb_cont";

        public static final String ERR_HTC_FASTBOOT = "HTC_Fastboot_cont_excep";
        public static final String ERR_PTP = "ptp_cont_excep";
        public static final String ERR_ADB = "adb_cont_excep";
    }

    public static void sendEvent(Context context, String category, String action,
                                 String label, Long value) {

        if (context == null) {
            return;
        }

        // Create new intent
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, OneClickReportingIntentService.class));

        // append
        intent.putExtra(Fields.EVENT_CATEGORY, category);
        intent.putExtra(Fields.EVENT_ACTION, action);

        // check if exist
        if (label != null) {
            intent.putExtra(Fields.EVENT_LABEL, label);
        }

        if (value != null) {
            intent.putExtra(Fields.EVENT_VALUE, value);
        }

        context.startService(intent);
    }

    public static void sendEvent(Context context, String category, String action) {
        sendEvent(context, category, action, null, null);
    }

    public static void sendEvent(Context context, String category, String action, String label) {
        sendEvent(context, category, action, label, null);
    }
}

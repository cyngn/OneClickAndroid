/*
 * Copyright (C) 2014 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.oneclick;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class Analytics {

	public static final String ADB_ALREADY_ENABLED = "apk.adbAlreadyEnabled";
	public static final String ADB_ENABLED = "apk.adbEnabled";
	public static final String CONTINUE_ON_MAC = "apk.continueOnMac";
	public static final String CONTINUE_ON_WINDOWS = "apk.continueOnWindows";
	public static final String DEVICE_ALREADY_UNPLUGGED = "apk.deviceAlreadyUnplugged";
	public static final String PTP_ALREADY_ENABLED = "apk.ptpAlreadyEnabled";
	public static final String STARTED = "apk.started";
	public static final String TERMS_ACCEPTED = "apk.termsAccepted";
	public static final String UNPLUG_DEVICE = "apk.unplugDevice";
	

    private static Tracker sTracker;
 
    private synchronized static Tracker getTracker(Context context) {
        if (sTracker == null) {
            GoogleAnalytics ga = GoogleAnalytics.getInstance(context);
            sTracker = ga.newTracker(context.getString(R.string.ga_trackingId));
            sTracker.setUseSecure(true);
            sTracker.setClientId(Utils.getUniqueID(context));
        }
        return sTracker;
    }
 
    public static void sendEvent(Context context, String category) {
        sendEvent(context, category, Utils.getDevice());
    }
 
    public static void sendEvent(Context context, String category, String action) {
        Tracker tracker = getTracker(context);
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action).build());
    }
}
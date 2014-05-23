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

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger.LogLevel;
import com.google.android.gms.analytics.Tracker;

public class Analytics {
	private static Tracker mTracker;
	public static final String ADB_ALREADY_ENABLED = "apk.adbAlreadyEnabled";
	public static final String ADB_ENABLED = "apk.adbEnabled";
	public static final String CONTINUE_ON_MAC = "apk.continueOnMac";
	public static final String CONTINUE_ON_WINDOWS = "apk.continueOnWindows";
	public static final String DEVICE_ALREADY_UNPLUGGED = "apk.deviceAlreadyUnplugged";
	public static final String PTP_ALREADY_ENABLED = "apk.ptpAlreadyEnabled";
	public static final String STARTED = "apk.started";
	public static final String TERMS_ACCEPTED = "apk.termsAccepted";
	public static final String UNPLUG_DEVICE = "apk.unplugDevice";
	
	private static final Boolean DEBUG = true;

	private static void init(Context applicationContext) {
		if (DEBUG) GoogleAnalytics.getInstance(applicationContext).getLogger().setLogLevel(LogLevel.VERBOSE);

		GoogleAnalytics ga = GoogleAnalytics.getInstance(applicationContext);
		mTracker = ga.newTracker(applicationContext.getString(R.string.ga_trackingId));
		mTracker.setUseSecure(true);

		mTracker.setClientId(Utils.getUniqueID(applicationContext));
	}

	public static void send(Context context, String category) {
		send(context, category, Utils.getDevice());
	}
	
	public static void send(Context context, String category, String action) {
		if (mTracker == null) {
			init(context);
		}

		mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).build());
		if (DEBUG) Log.i("Analytics", "sent GA ping with category: " + category + " and action: " + action);
	}
}

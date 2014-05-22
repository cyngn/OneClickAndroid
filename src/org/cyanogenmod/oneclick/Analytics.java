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
import android.app.Dialog;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class Analytics {
	private static Tracker mTracker;
	
	public static void Init(Context applicationContext, Activity currentActivity) {
        // let's make sure play services is up to date, or analytics probably won't work
        Integer resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(currentActivity);
        if (resultCode != ConnectionResult.SUCCESS) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, currentActivity, 0);
            if (dialog != null) {
                dialog.show();
            }
        }
				
        GoogleAnalytics ga = GoogleAnalytics.getInstance(applicationContext);
        mTracker = ga.newTracker(applicationContext.getString(R.string.ga_trackingId));
        mTracker.setUseSecure(true);

        mTracker.setClientId(getUniqueID(applicationContext));
	}
	
	public static void Send(String category)
	{
		if (mTracker == null) {
			Log.e("Analytics", "you have to Init() before using analytics.");
			return;
		}
		
		String action = Utils.getDevice();
		mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).build());
		//Log.i("Analytics", "sent GA ping with category: " + category + " and action: " + action);
	}
	
	private static String getUniqueID(Context applicationContext) {
		return Utils.md5(Settings.Secure.getString(applicationContext.getContentResolver(), Settings.Secure.ANDROID_ID));		
	}
}

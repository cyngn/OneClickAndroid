package org.cyanogenmod.oneclick;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;

import org.cyanogenmod.oneclick.OneClickStats.Fields;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class OneClickReportingIntentService extends IntentService {

    /* package */
    static final String TAG = "OneClickStats";

    private Tracker mTracker;
    private GoogleAnalytics mGa;

    public OneClickReportingIntentService() {
        super(OneClickReportingIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGa = GoogleAnalytics.getInstance(this);
        mTracker = mGa.newTracker(getResources().getString(R.string.ga_trackingId));

        mTracker.setUseSecure(true);

        // Set Logger verbosity.
        mGa.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // send individual events in background
        Bundle bundle = intent.getExtras();
        if (!bundle.isEmpty()) {
            if (mTracker == null) {
                Log.w(TAG, "Tracker missing");
                return;
            }
            String category = bundle.getString(Fields.EVENT_CATEGORY);
            String action = bundle.getString(Fields.EVENT_ACTION);
            HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder(category, action);

            eventBuilder.setCustomDimension(1, SystemProperties.get("ro.product.brand"));
            eventBuilder.setCustomDimension(2, SystemProperties.get("ro.product.device"));
            eventBuilder.setCustomDimension(3, SystemProperties.get("ro.product.model"));
            eventBuilder.setCustomDimension(4, SystemProperties.get("ro.product.name"));
            eventBuilder.setCustomDimension(5, SystemProperties.get("ro.product.board"));
            eventBuilder.setCustomDimension(6, SystemProperties.get("ro.product.cpu.abi"));
            
            if (bundle.containsKey(Fields.EVENT_LABEL)) {
                eventBuilder.setLabel(bundle.getString(Fields.EVENT_LABEL));
            }
            if (bundle.containsKey(Fields.EVENT_VALUE)) {
                eventBuilder.setValue(bundle.getLong(Fields.EVENT_VALUE));
            }

            Log.w(TAG, "onHandleIntent/" + category + "/" +action + "/" +
                bundle.getString(Fields.EVENT_LABEL) + "/" + bundle.getLong(Fields.EVENT_VALUE));
            mTracker.send(eventBuilder.build());
        }
    }
}

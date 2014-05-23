package org.cyanogenmod.oneclick;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.welcome);

        // let's make sure play services is up to date, or analytics probably won't work
        Integer resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
					public void onDismiss(DialogInterface dialog) {
						// bail
			            finish();
					}
				});

                dialog.show();
            }
        }
        
        Analytics.sendEvent(getApplicationContext(), Analytics.STARTED);

        findViewById(R.id.begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, DisclaimerActivity.class));
            }
        });
    }
}

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;

public class FinishActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.windows);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
        if (!adbIsEnabled()) {
            // oh snap. we need to start over 
            startActivity(new Intent(getBaseContext(), StartActivity.class));
            finish();
        }
    }
    
	@SuppressWarnings("deprecation")
	private boolean adbIsEnabled() {
		return (Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1);
	}
}

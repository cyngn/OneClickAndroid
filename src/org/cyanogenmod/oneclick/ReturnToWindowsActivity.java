package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.os.Bundle;

public class ReturnToWindowsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics.send(getApplicationContext(), Analytics.CONTINUE_ON_WINDOWS);
        setContentView(R.layout.returntowindows);
    }
}

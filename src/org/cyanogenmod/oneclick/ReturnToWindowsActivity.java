package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.os.Bundle;

public class ReturnToWindowsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics.Send("apk.continueOnWindows");
        setContentView(R.layout.returntowindows);
    }
}

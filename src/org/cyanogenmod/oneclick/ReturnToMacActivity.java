package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.os.Bundle;

public class ReturnToMacActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics.Send("apk.continueOnMac");
        setContentView(R.layout.returntomac);
    }
}

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class CompatibilityActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneClickStats.sendEvent(this, OneClickStats.Categories.PAGE_SHOWN,
            OneClickStats.Actions.PAGE_COMPAT_CHECK);
        setContentView(R.layout.unsupported);

        // Make links clickable
        TextView textView = (TextView) findViewById(R.id.unsupported_text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        if (Utils.isDeviceSupported()) {
            startMainActivity();

            // Finish this activity so it does not show up when the user presses the back button
            finish();
        }

        findViewById(R.id.continue_anyway).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneClickStats.sendEvent(view.getContext(),
                    OneClickStats.Categories.BUTTON_CLICK,
                    OneClickStats.Actions.BTN_UNSUPP_CONT);
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(getBaseContext(), StartActivity.class));
    }
}

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        OneClickStats.sendEvent(this, OneClickStats.Categories.PAGE_SHOWN,
            OneClickStats.Actions.PAGE_WELCOME);

        findViewById(R.id.begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneClickStats.sendEvent(view.getContext(), OneClickStats.Categories.BUTTON_CLICK,
                    OneClickStats.Actions.BTN_WELCOME);
                startActivity(new Intent(StartActivity.this, DisclaimerActivity.class));
            }
        });
    }
}

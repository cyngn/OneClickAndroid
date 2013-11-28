package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by koush on 11/28/13.
 */
public class DisclaimerActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disclaimer);

        findViewById(R.id.i_agree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisclaimerActivity.this, UsbActivity.class));
            }
        });
    }
}

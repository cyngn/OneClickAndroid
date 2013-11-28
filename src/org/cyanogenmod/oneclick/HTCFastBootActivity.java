package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class HTCFastBootActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (fastBootIsDisabled()) {
            startActivity(new Intent(getBaseContext(), UnplugDeviceActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.htc_fast_boot);

        ImageView instructionView = (ImageView) findViewById(R.id.htc_fast_boot_instructions);

        DecelerateInterpolator interpolator = new DecelerateInterpolator(2.0f);

        AnimationSet instructionAnimations = new AnimationSet(true);
        instructionAnimations.setInterpolator(interpolator);
        TranslateAnimation instructionMoveAnimation = new TranslateAnimation(0, 0, 250, 0);
        instructionMoveAnimation.setDuration(1000);
        instructionMoveAnimation.setStartTime(Animation.START_ON_FIRST_FRAME);
        instructionAnimations.addAnimation(instructionMoveAnimation);

        // we want them to read the instructions first! so we give them a few seconds
        AlphaAnimation instructionFadeAnimation = new AlphaAnimation(0.0f, 1.0f);
        instructionFadeAnimation.setDuration(1000);
        instructionFadeAnimation.setStartOffset(500);
        instructionFadeAnimation.setStartTime(Animation.START_ON_FIRST_FRAME);
        instructionAnimations.addAnimation(instructionFadeAnimation);

        instructionView.setAnimation(instructionAnimations);

        // continue button should take even longer
        AlphaAnimation buttonAnimation = new AlphaAnimation(0.0f, 1.0f);
        buttonAnimation.setDuration(750);
        buttonAnimation.setStartTime(Animation.START_ON_FIRST_FRAME);
        buttonAnimation.setStartOffset(1000);
        findViewById(R.id.next).setAnimation(buttonAnimation);

        OnClickListener openHtcFastBootListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(htcPowerManager);
                try {
                    startActivity(intent);
                    startService(new Intent(getBaseContext(), HTCFastBootMonitorService.class));
                } catch (ActivityNotFoundException e) {
                    // we want to know if this happens, right?
                }
            }
        };

        findViewById(R.id.next).setOnClickListener(openHtcFastBootListener);
    }

    ComponentName htcPowerManager = new ComponentName("com.htc.htcpowermanager", "com.htc.htcpowermanager.PowerManagerActivity");

    @Override
    public void onResume() {
        super.onResume();

        if (fastBootIsDisabled()) {
            finish();
        }
    }

    private boolean fastBootIsDisabled() {
        try {
            // check the activity
            getPackageManager().getActivityInfo(htcPowerManager, 0);
        } catch (Exception e) {
            return true;
        }

        return Settings.Secure.getInt(getContentResolver(), "enable_fastboot", 1) == 0;
    }
}

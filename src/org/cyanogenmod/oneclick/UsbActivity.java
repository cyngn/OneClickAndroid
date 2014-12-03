package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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

public class UsbActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OneClickStats.sendEvent(this,
                OneClickStats.Categories.PAGE_SHOWN, OneClickStats.Actions.PAGE_ADB);

        if (adbIsEnabled()) {
            startActivity(new Intent(getBaseContext(), PtpActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.usb);

        ImageView instructionView = (ImageView) findViewById(R.id.usb_instructions);

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

        OnClickListener openUsbListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.DevelopmentSettings");
                try {
                    OneClickStats.sendEvent(view.getContext(),
                            OneClickStats.Categories.BUTTON_CLICK, OneClickStats.Actions.BTN_ADB);
                    startActivity(intent);
                    startService(new Intent(getBaseContext(), UsbDebuggingMonitorService.class));
                } catch (ActivityNotFoundException e) {
                    // we want to know if this happens, right?
                    OneClickStats.sendEvent(view.getContext(),
                            OneClickStats.Categories.SWITCH_ERROR, OneClickStats.Actions.ERR_ADB);
                }
            }
        };

        findViewById(R.id.next).setOnClickListener(openUsbListener);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adbIsEnabled()) {
            finish();
        }
    }

    @SuppressWarnings("deprecation")
    private boolean adbIsEnabled() {
        return (Settings.Secure.getInt(getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1);
    }
}

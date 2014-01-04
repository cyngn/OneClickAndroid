/*
 * Copyright 2014 Cyanogen, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.oneclick;

import android.app.Activity;
import android.content.ComponentName;

public class HTCFastBootMonitorService extends MonitorService {
    public static final ComponentName HTC_POWER_MANAGER = new ComponentName("com.htc.htcpowermanager", "com.htc.htcpowermanager.PowerManagerActivity");

    @Override
    protected boolean canContinue() {
        try {
            getPackageManager().getActivityInfo(HTC_POWER_MANAGER, 0);
        } catch (Exception e) {
            return true;
        }

        return Utils.fastbootIsDisabled(getBaseContext());
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return UnplugDeviceActivity.class;
    }
}

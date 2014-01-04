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
import android.provider.Settings;

public class HTCFastBootMonitorService extends MonitorService {
    ComponentName htcPowerManager = new ComponentName("com.htc.htcpowermanager", "com.htc.htcpowermanager.PowerManagerActivity");

    @Override
    protected boolean canContinue() {
        try {
            getPackageManager().getActivityInfo(htcPowerManager, 0);
        } catch (Exception e) {
            return true;
        }

        return Settings.Secure.getInt(getContentResolver(), "enable_fastboot", 1) == 0;
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return UnplugDeviceActivity.class;
    }
}

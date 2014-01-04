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
import android.os.SystemProperties;

public class PtpMonitorService extends MonitorService {
    @Override
    protected boolean canContinue() {
        String usbConfig = SystemProperties.get("persist.sys.usb.config");
        if (usbConfig != null && usbConfig.contains("mtp")) {
            return false;
        }
        return true;
    }

    @Override
    protected Class<? extends Activity> getNextActivityClass() {
        return HTCFastBootActivity.class;
    }
}

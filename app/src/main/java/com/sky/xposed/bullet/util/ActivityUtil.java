/*
 * Copyright (c) 2018 The sky Authors.
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

package com.sky.xposed.bullet.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.sky.xposed.common.util.Alog;

/**
 * Created by sky on 2018/9/12.
 */
public class ActivityUtil {

    private ActivityUtil() {

    }

    public static boolean startActivity(Context context, Intent intent) {
        try {
            // 启动Activity
            context.startActivity(intent);
            return true;
        } catch (Throwable tr) {
            Alog.e("启动Activity异常", tr);
        }
        return false;
    }

    public static boolean startNearbySmartisanerActivity(Context context) {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(
                "com.bullet.messenger",
                "com.smartisan.flashim.smartisanmap.view.NearbySmartisanerActivity"));

        return startActivity(context, intent);
    }
}

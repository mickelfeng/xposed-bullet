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

package com.sky.xposed.bullet.hook;

import android.content.Context;

import com.sky.xposed.common.util.Alog;
import com.sky.xposed.common.util.PackageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sky on 18-6-10.
 */
public class VersionManager {

    private final static Map<String, Class<? extends Config>> CONFIG_MAP = new HashMap<>();

    static {
    }

    private Context mContext;
    private Config mVersionConfig;

    public VersionManager(HookManager hookManager) {
        mContext = hookManager.getContext();
    }

    public boolean isSupportVersion() {

        PackageUtil.SimplePackageInfo info = getPackageInfo();

        if (info == null) return false;

        return CONFIG_MAP.containsKey(info.getVersionName());
    }

    public Config getSupportConfig() {

        PackageUtil.SimplePackageInfo info = getPackageInfo();

        if (info == null) return null;

        return getSupportConfig(CONFIG_MAP.get(info.getVersionName()));
    }

    private Config getSupportConfig(Class<? extends Config> vClass) {

        if (vClass == null) return null;

        if (mVersionConfig == null) {
            try {
                // 创建实例
                mVersionConfig = vClass.newInstance();
            } catch (Throwable tr) {
                Alog.e("创建版本配置异常", tr);
            }
        }
        return mVersionConfig;
    }

    private PackageUtil.SimplePackageInfo getPackageInfo() {
        return PackageUtil.getSimplePackageInfo(mContext, mContext.getPackageName());
    }



    public static class Config {

    }
}

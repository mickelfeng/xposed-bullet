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

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.bullet.BuildConfig;
import com.sky.xposed.bullet.hook.base.BaseHook;
import com.sky.xposed.bullet.util.FindUtil;
import com.sky.xposed.common.util.Alog;
import com.sky.xposed.javax.MethodHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 2018/9/5.
 */
public class BulletHook extends BaseHook {

    @Override
    public void onHandleLoadPackage(XC_LoadPackage.LoadPackageParam param) {

        if (BuildConfig.DEBUG) testHook();

        injectionUISettings();
    }

    private void injectionUISettings() {

        findMethod(
                "com.smartisan.flashim.main.fragment.HomeFragment",
                "a", int.class)
                .hook(new MethodHook.AfterCallback() {
                    @Override
                    public void onAfter(XC_MethodHook.MethodHookParam param) {

                        Activity activity = (Activity) XposedHelpers
                                .callMethod(param.thisObject, "getActivity");
                        handlerHomeNearby((ViewGroup) XposedHelpers
                                .callMethod(activity, "getTitleBar"));
                    }
                });
    }

    private void handlerHomeNearby(ViewGroup titleBar) {

        View imageView = FindUtil.findImageView(titleBar);

        if (imageView == null) return;

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = new Intent();
                intent.setComponent(new ComponentName(
                        "com.bullet.messenger",
                        "com.smartisan.flashim.smartisanmap.view.NearbySmartisanerActivity"));
                mContext.startActivity(intent);
                return true;
            }
        });
    }

    public void onModifyValue(String key, Object value) {

    }

    private void testHook() {

        Alog.d(">>>>>>>>>>>>> " + findClass("com.smartisan.flashim.main.activity.MainActivity"));
        Alog.d(">>>>>>>>>>>>> " + findClass("com.smartisan.flashim.smartisanmap.view.NearbySmartisanerActivity"));

//        findMethod(
//                "android.app.Dialog", "show")
//                .hook(new MethodHook.BeforeCallback() {
//                    @Override
//                    public void onBefore(XC_MethodHook.MethodHookParam param) {
//
//                        Alog.d(">>>>>>>>>>>>>> dialog " + param.thisObject);
//                    }
//                });
//
//        findMethod(
//                "android.support.v4.app.Fragment",
//                "onCreate", Bundle.class)
//                .hook(new MethodHook.BeforeCallback() {
//                    @Override
//                    public void onBefore(XC_MethodHook.MethodHookParam param) {
//
//                        Alog.d(">>>>>>>>>>>>>> Fragment " + param.thisObject.getClass());
//                    }
//                });
//
//        findMethod(
//                PopupWindow.class,
//                "showAtLocation", View.class, int.class, int.class, int.class)
//                .hook(new MethodHook.BeforeCallback() {
//                    @Override
//                    public void onBefore(XC_MethodHook.MethodHookParam param) {
//
//                        Alog.d(">>>>>>>>>>>>>> showAtLocation1 " + param.thisObject);
//                    }
//                });



    }
}

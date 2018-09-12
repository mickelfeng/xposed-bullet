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
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky.xposed.bullet.BuildConfig;
import com.sky.xposed.bullet.hook.base.BaseHook;
import com.sky.xposed.bullet.util.ActivityUtil;
import com.sky.xposed.common.util.DisplayUtil;
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
                "com.smartisan.flashim.contact.fragment.AddFriendFragment",
                "onViewCreated", View.class, Bundle.class)
                .hook(new MethodHook.AfterCallback() {
                    @Override
                    public void onAfter(XC_MethodHook.MethodHookParam param) {

                        Activity activity = (Activity) XposedHelpers
                                .callMethod(param.thisObject, "getActivity");
                        handlerFriendNearby((RelativeLayout) XposedHelpers
                                .callMethod(activity, "getTitleBar"));
                    }
                });
    }

    private void handlerFriendNearby(RelativeLayout titleBar) {

        if (titleBar == null) return;

        TextView  nearbyView = new TextView(mContext);
        nearbyView.setGravity(Gravity.CENTER);
        nearbyView.setText("附近群");
        nearbyView.setTextColor(Color.WHITE);
        nearbyView.setTextSize(14);
        nearbyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 跳转到附近人界面
                ActivityUtil.startNearbySmartisanerActivity(mContext);
            }
        });

        // 布局属性
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                DisplayUtil.dip2px(mContext, 70), RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        // 添加到标题栏中
        titleBar.addView(nearbyView, params);
    }

    public void onModifyValue(String key, Object value) {

    }

    private void testHook() {

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

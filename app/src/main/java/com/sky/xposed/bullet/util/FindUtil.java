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

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sky on 2018/9/11.
 */
public class FindUtil {

    private FindUtil() {

    }

    public static View findImageView(ViewGroup viewGroup) {

        if (viewGroup == null) return null;

        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++) {

            View childView = viewGroup.getChildAt(i);

            if (childView instanceof ImageView) {
                return childView;
            }
        }
        return null;
    }
}

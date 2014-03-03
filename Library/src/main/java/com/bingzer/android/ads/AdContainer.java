/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * This class represents and Ad container.
 */
public class AdContainer extends LinearLayout {

    public static final int BANNER = 0;
    public static final int FULL_BANNER = 1;
    public static final int LEADERBOARD = 2;
    public static final int MEDIUM_RECTANGLE = 3;
    public static final int WIDE_SKYSCRAPER = 4;
    public static final int SMART_BANNER = 5;

    public AdContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setBackgroundResource(android.R.color.transparent);
    }

    /**
     * Default children's params.
     * Width is LayoutParams.MATCH_PARENT. Height is LayoutParams.WRAP_CONTENT
     */
    public static LayoutParams params() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        return params;
    }
}

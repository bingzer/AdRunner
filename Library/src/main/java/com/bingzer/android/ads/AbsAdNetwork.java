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
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.ads.Range;

public abstract class AbsAdNetwork<T extends View> implements IAdNetwork {

    protected boolean enabled = true;
    protected Range range = new Range();
    protected String pubId;
    protected Callback callback;
    protected T adView;
    protected int maximumHeight = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    public IAdNetwork enable(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public View getView() {
        return adView;
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

    /**
     * Sets the range
     */
    @Override
    public IAdNetwork range(Range range) {
        this.range = range;
        return this;
    }

    /**
     * Returns the range
     */
    @Override
    public Range range() {
        return range;
    }

    /////////////////////////////////////////////////////////

    public void setMaximumHeight(int maximumHeight){
        this.maximumHeight = maximumHeight;
    }

    protected LinearLayout.LayoutParams params(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, maximumHeight);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        return params;
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    @Override
    public abstract String name();

    @Override
    public abstract View load(Context context, String... keywords);

    @Override
    public abstract IAdNetwork unload();

    @Override
    public abstract IAdNetwork showAd(Callback callback);

}

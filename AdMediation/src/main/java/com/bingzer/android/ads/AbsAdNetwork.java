package com.bingzer.android.ads;

import android.content.Context;
import android.view.View;

import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.ads.Range;

abstract class AbsAdNetwork<T extends View> implements IAdNetwork {

    boolean enabled = true;
    Range range = new Range();
    String pubId;
    Callback callback;
    T adView;

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

package com.bingzer.android.ads;


import android.content.Context;
import android.view.View;

/**
 * Represents an ad network
 *
 * @author 11856
 */
public interface IAdNetwork {

    IAdNetwork enable(boolean enabled);

    /**
     * Returns the name
     *
     * @return the name of this ad network
     */
    String name();

    /**
     * True if enabled
     *
     * @return
     */
    boolean enabled();

    /**
     * Sets the range
     *
     * @param range
     * @return
     */
    IAdNetwork range(Range range);

    /**
     * Returns the range
     *
     * @return
     */
    Range range();

    /**
     * Returns the view (this may be null)
     * if {@link #load(android.content.Context, String...)} has not
     * been called yet
     *
     * @return
     */
    View getView();

    /**
     * Loads and return the view
     *
     * @param context
     * @param keywords
     * @return
     */
    View load(Context context, String... keywords);

    /**
     * Show ads
     */
    IAdNetwork showAd(Callback callback);

    /**
     * Unload views
     */
    IAdNetwork unload();


    public static interface Callback {

        void onAdReceived(IAdNetwork network, Result result);

    }
}

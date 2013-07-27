package com.bingzer.android.ads;

import android.content.Context;

/**
 * Represents an Ad client.
 * This should be implemented in inside an activity
 * where you would like to display the ad
 *
 * @author 11856
 */
public interface IAdClient extends IAdNetwork.Callback {

    /**
     * Returns 'context'
     *
     * @return
     */
    Context getContext();

    /**
     * Returns the ad container.
     *
     * @return
     */
    AdContainer getAdContainer();

    /**
     * Returns keywords
     *
     * @return
     */
    String[] getKeywords();

    /**
     * Returns all available networks
     *
     * @return
     */
    AdNetworkList getAdNetworkList();

    /**
     * <code>task</code> should be ran inside
     * the UI Thread
     *
     * @param task
     */
    void runOnUiThread(Runnable task);

    /**
     * True if client would like to display ad
     *
     * @return
     */
    boolean requestingAd();

}

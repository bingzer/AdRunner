package com.bingzer.android.ads;


/**
 * Represents an AdRunner.
 * One can create and IAdRunner by calling
 * {@link AdMediator#create(com.bingzer.android.ads.IAdClient)}
 *
 * @author Ricky Tobing
 */
public interface IAdRunner extends IAdNetwork.Callback {

    /**
     * Default network switch count
     */
    int DEFAULT_NETWORK_SWITCH_COUNT = 5;

    /**
     * 300 seconds = 5 minutes
     *
     * @see #setMaxSleep(int)
     */
    int MAX_SLEEP_TIME = 300;

    /**
     * 30 seconds
     *
     * @see #setMinSleep(int)
     */
    int MIN_SLEEP_TIME = 30;

    /**
     * Should put this method in {@link android.app.Activity.onResume()}
     * if implemented inside the {@link android.app.Activity}
     */
    void onResume();

    /**
     * Should put this method in {@link android.app.Activity.onPause()}
     * if implemented inside the {@link android.app.Activity}
     */
    void onPause();

    /**
     * Determines the number of time for the AdRunner
     * to switch to other network
     *
     * @param switchCount
     */
    void setNetworkSwitchCount(int switchCount);

    /**
     * Sets the maximum of sleep. The runner
     * will 'sleep' in a random time between the
     * range of 'minimum sleep time' and the
     * 'maximum sleep time'
     * The <code>seconds</code> must be over or
     * equal to {@link com.bingzer.android.ads.IAdRunner#MIN_SLEEP_TIME}
     *
     * @param seconds
     * @see #setMaxSleep(int)
     */
    void setMinSleep(int seconds);

    /**
     * Sets the maximum of sleep. The runner
     * will 'sleep' in a random time between the
     * range of 'minimum sleep time' and the
     * 'maximum sleep time'
     * The <code>seconds</code> must be less or
     * equal to {@link com.bingzer.android.ads.IAdRunner#MAX_SLEEP_TIME}
     *
     * @param seconds
     * @see #setMinSleep(int)
     */
    void setMaxSleep(int seconds);

}

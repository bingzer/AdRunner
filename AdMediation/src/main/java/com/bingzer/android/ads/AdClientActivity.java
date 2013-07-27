package com.bingzer.android.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.bingzer.android.Result;

/**
 * Activity add ads
 * Created by 11856 on 7/11/13.
 */
public abstract class AdClientActivity extends Activity implements IAdClient{

    protected IAdRunner adRunner;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        adRunner = AdMediator.instance().create(this);
    }

    /**
     * Returns 'context'
     *
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Returns the ad container.
     *
     * @return
     */
    @Override public abstract AdContainer getAdContainer();

    /**
     * Returns keywords
     *
     * @return
     */
    @Override
    public String[] getKeywords() {
        return new String[0];
    }

    /**
     * Returns all available networks
     *
     * @return
     */
    @Override
    public abstract  AdNetworkList getAdNetworkList();

    /**
     * True if client would like to display ad
     *
     * @return
     */
    @Override
    public boolean requestingAd() {
        return false;
    }

    @Override
    public void onAdReceived(IAdNetwork network, Result result) {

    }
}

package com.bingzer.android.ads;

import android.content.Context;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class AdMobNetwork implements IAdNetwork {

    private IAdNetwork adNetwork;

    public AdMobNetwork(Context context, String pubId){
        this(context, AdContainer.SMART_BANNER, pubId);
    }

    public AdMobNetwork(Context context, int adSize, String pubId){
        // define if there's GMS available
        if(GooglePlayServicesUtil.isGooglePlayServicesAvailable(context.getApplicationContext()) == ConnectionResult.SUCCESS)
            adNetwork = new GoogleAdsNetwork(adSize, pubId);
        else
            adNetwork = new AdMobSdkNetwork(adSize, pubId);
    }

    @Override
    public String getName() {
        return adNetwork.getName();
    }

    @Override
    public void setEnabled(boolean enabled) {
        adNetwork.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return adNetwork.isEnabled();
    }

    @Override
    public void setRange(Range range) {
        adNetwork.setRange(range);
    }

    @Override
    public Range getRange() {
        return adNetwork.getRange();
    }

    @Override
    public View getView() {
        return adNetwork.getView();
    }

    @Override
    public View load(Context context, String... keywords) {
        return adNetwork.load(context, keywords);
    }

    @Override
    public IAdNetwork showAd(Callback callback) {
        return adNetwork.showAd(callback);
    }

    @Override
    public IAdNetwork unload() {
        return adNetwork.unload();
    }

    @Override
    public boolean onBackPressed() {
        return adNetwork.onBackPressed();
    }
}

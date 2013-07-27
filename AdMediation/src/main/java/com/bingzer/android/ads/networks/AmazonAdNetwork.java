package com.bingzer.android.ads.networks;

import android.content.Context;
import android.view.View;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.bingzer.android.Helper;
import com.bingzer.android.Result;
import com.bingzer.android.ads.AdContainer;
import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.app.Res;

public final class AmazonAdNetwork extends AbsAdNetwork<AdLayout> implements AdListener {
    private AdTargetingOptions ops;
    private AdLayout.AdSize adSize;

    public AmazonAdNetwork(String pubId) {
        this(AdLayout.AdSize.AD_SIZE_CUSTOM, pubId);
    }

    public AmazonAdNetwork(AdLayout.AdSize adSize, String pubId) {
        this.pubId = pubId;
        this.ops = new AdTargetingOptions();
        this.adSize = adSize;
    }

    @Override
    public String name() {
        return "Amazon";
    }

    @Override
    public View load(Context context, String... keywords) {
        if (adView == null) {
            AdRegistration.setAppKey(context, pubId);
            if (adSize == AdLayout.AdSize.AD_SIZE_CUSTOM)
                adView = new AdLayout(context, getAdSize());
            else adView = new AdLayout(context, adSize);

            adView.setListener(this);
            adView.setBackgroundResource(android.R.color.transparent);
            adView.setLayoutParams(AdContainer.params());
        }
        ops.setGender(Helper.getRandom(AdTargetingOptions.Gender.MALE, AdTargetingOptions.Gender.FEMALE));
        ops.setAge(Helper.getRandom(10, 50));
        return adView;
    }

    @Override
    public IAdNetwork unload() {
        adView.destroy();
        adView = null;
        return this;
    }

    @Override
    public IAdNetwork showAd(Callback callback) {
        this.callback = callback;
        adView.loadAd(ops);
        return this;
    }

    private AdLayout.AdSize getAdSize() {
        AdLayout.AdSize adSize = AdLayout.AdSize.AD_SIZE_320x50;
        /*
        if (Res.getScreenWidth() >= 1024 && Res.getScreenHeight() > 800)
            adSize = AdLayout.AdSize.AD_SIZE_1024x50;
        else if (Res.getScreenWidth() >= 728 && Res.getScreenHeight() > 700)
            adSize = AdLayout.AdSize.AD_SIZE_728x90;
        else if (Res.getScreenWidth() >= 600 && Res.getScreenHeight() > 700)
            adSize = AdLayout.AdSize.AD_SIZE_600x90;
        */

        return adSize;
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    @Override
    public void onAdCollapsed(AdLayout ad) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAdExpanded(AdLayout ad) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAdFailedToLoad(AdLayout ad, AdError err) {
        callback.onAdReceived(this, new Result.Bad(err.getMessage()));
    }

    @Override
    public void onAdLoaded(AdLayout ad, AdProperties prop) {
        callback.onAdReceived(this, new Result.Good());
    }

}

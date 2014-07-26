package com.bingzer.android.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;

@SuppressWarnings("UnusedDeclaration")
public final class AmazonAdNetwork extends AbsAdNetwork<AdLayout> implements AdListener{
    public static final int SIZE_300x50  = 0;
    public static final int SIZE_320x50  = 1;
    public static final int SIZE_600x90  = 2;
    public static final int SIZE_728x90  = 3;
    public static final int SIZE_1024x50 = 4;
    public static final int SIZE_AUTO    = 5;

	private AdTargetingOptions ops;
	private AdSize adSize;
	
	public AmazonAdNetwork(String pubId){
		this(SIZE_AUTO, pubId);
	}

    public AmazonAdNetwork(int size, String pubId){
		this.pubId = pubId;
		this.ops = new AdTargetingOptions();

        switch (size){
            default:
            case SIZE_AUTO:
                adSize = AdSize.SIZE_AUTO;
                break;
            case SIZE_300x50:
                adSize = AdSize.SIZE_300x50;
                break;
            case SIZE_320x50:
                adSize = AdSize.SIZE_320x50;
                break;
            case SIZE_600x90:
                adSize = AdSize.SIZE_600x90;
                break;
            case SIZE_728x90:
                adSize = AdSize.SIZE_728x90;
                break;
            case SIZE_1024x50:
                adSize = AdSize.SIZE_1024x50;
                break;
        }
	}

	@Override
	public String getName() {
		return "Amazon";
	}

	@Override
	public View load(Context context, String... keywords) {
        if(!(context instanceof Activity))
            throw new IllegalArgumentException("Amazon load() is expecting an Activity from context param");
		if(adView == null){
            // enable testing if pubId is null or empty
            if(pubId == null || pubId.length() == 0)
                AdRegistration.enableTesting(true);
			else
                AdRegistration.setAppKey(pubId);
            // auto size thing
            adView = new AdLayout((Activity)context, adSize);

			adView.setListener(this);
			adView.setLayoutParams(params());
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

    @Override
    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        callback.onAdReceived(this, AdResult.Good);
    }

    @Override
    public void onAdFailedToLoad(Ad ad, AdError adError) {
        callback.onAdReceived(this, AdResult.Bad.message(adError.getMessage()));
    }

    @Override
    public void onAdExpanded(Ad ad) {

    }

    @Override
    public void onAdCollapsed(Ad ad) {

    }

    @Override
    public void onAdDismissed(Ad ad) {

    }
}

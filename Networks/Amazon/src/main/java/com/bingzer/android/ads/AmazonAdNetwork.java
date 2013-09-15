package com.bingzer.android.ads;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;

public final class AmazonAdNetwork extends AbsAdNetwork<AdLayout> implements AdListener{
	private AdTargetingOptions ops;
	private AdSize adSize;
	
	public AmazonAdNetwork(String pubId){
		this(AdSize.SIZE_320x50, pubId);
	}
	
	public AmazonAdNetwork(AdSize adSize, String pubId){
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
        if(!(context instanceof Activity))
            throw new IllegalArgumentException("Amazon load() is expecting an Activity from context param");
		if(adView == null){			
			AdRegistration.setAppKey(pubId);
            // auto size thing
            if(adSize == AdSize.SIZE_AUTO) adView = new AdLayout((Activity) context, AdSize.SIZE_320x50);
            else adView = new AdLayout((Activity)context, adSize);

			adView.setListener(this);
			adView.setBackgroundResource(android.R.color.transparent);
			adView.setLayoutParams(params());
		}
		ops.setGender(Helper.getRandom(AdTargetingOptions.Gender.MALE, AdTargetingOptions.Gender.FEMALE));
		ops.setAge(Helper.getRandom(10, 50));

		return adView;
	}

    private LayoutParams params(){
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        return params;
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
		callback.onAdReceived(this, Result.Bad.message(err.getMessage()));
	}

	@Override
	public void onAdLoaded(AdLayout ad, AdProperties prop) {
		callback.onAdReceived(this, Result.Good);
	}

}

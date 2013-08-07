package com.bingzer.android.ads;

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.banner.AdView;
import com.bingzer.android.ads.AbsAdNetwork;
import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.ads.Result;

import android.content.Context;
import android.view.View;

public final class MobFoxNetwork extends AbsAdNetwork<AdView> implements AdListener{
	private boolean includeLocation = false;
	
	public MobFoxNetwork(String pubId){
		this(pubId, false);
	}
	
	public MobFoxNetwork(String pubId, boolean includeLocation){
		this.pubId = pubId;
		this.includeLocation = includeLocation;
	}

	@Override
	public String name() {
		return "Mobfox";
	}

	@Override
	public View load(Context context, String... keywords) {
		if(adView == null){
			adView = new AdView(context, "http://my.mobfox.com/request.php", pubId, includeLocation, true);
			adView.setAdListener(this);
		}
		return adView;
	}

	@Override
	public IAdNetwork showAd(Callback callback) {
		this.callback = callback;
		adView.resume();
		adView.loadNextAd();
		return this;
	}

	@Override
	public IAdNetwork unload() {
		adView.pause();
		adView.release();
		adView = null;
		return this;
	}

	
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	
	@Override
	public void adClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adClosed(Ad ad, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adShown(Ad ad, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adLoadSucceeded(Ad ad) {
		callback.onAdReceived(this, Result.Good);
	}

	@Override
	public void noAdFound() {
		callback.onAdReceived(this, Result.Bad.message("No Ad found"));
	}

}

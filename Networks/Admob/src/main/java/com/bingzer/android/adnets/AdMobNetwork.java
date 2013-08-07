package com.bingzer.android.adnets;

import com.bingzer.android.ads.AbsAdNetwork;
import com.bingzer.android.ads.AdContainer;
import com.bingzer.android.ads.Helper;
import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.ads.Result;
import com.bingzer.android.ads.TestDevices;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public final class AdMobNetwork extends AbsAdNetwork<AdView> implements AdListener{
	
	private AdRequest adRequest;
	private AdSize adSize;
	
	public AdMobNetwork(String pubId){
		this(AdSize.SMART_BANNER, pubId);
	}
	
	public AdMobNetwork(AdSize adSize, String pubId){
		this.pubId = pubId;
		this.adSize = adSize;
		this.adRequest = new AdRequest();
	}
	
	@Override
	public View getView(){
		return adView;
	}

	@Override
	public String name() {
		return "AdMob";
	}

	@Override
	public View load(Context context, String... keywords) {
		if(adView == null){
			// -- depending on the screen size..
	        adView = new AdView((Activity) context, adSize, pubId);
	        adView.setAdListener(this);
	        adView.setLayoutParams(AdContainer.params());
		}

		// load all keywords
		if(keywords != null){
			for(String keyword : keywords){
				adRequest.addKeyword(keyword);
			}
		}
		adRequest.setGender(Helper.getRandom(Gender.FEMALE, Gender.MALE));
		adRequest.setTestDevices(TestDevices.getDeviceSet());
		
		return adView;
	}

	@Override
	public IAdNetwork showAd(Callback callback) {
		this.callback = callback;
		adView.loadAd(adRequest);
		return this;
	}

	@Override
	public IAdNetwork unload() {
		adView.destroy();
		adView = null;
		return this;
	}
	
	/////////////////////////////////////////////////////////////

	@Override
	public void onDismissScreen(Ad ad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveApplication(Ad ad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPresentScreen(Ad ad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode err) {
		callback.onAdReceived(this, Result.Bad.message(err.name()));
	}

	@Override
	public void onReceiveAd(Ad ad) {
		callback.onAdReceived(this, Result.Good);
	}

}

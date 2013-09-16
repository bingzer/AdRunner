package com.bingzer.android.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.mwichbnnpignqawy.AdController;
import com.mwichbnnpignqawy.AdListener;

public class LeadBoltNetwork extends AbsAdNetwork<View> implements AdListener{

	private AdController adController;
	private String pubId;
	
	public LeadBoltNetwork(String pubId){
		this.pubId = pubId;
	}

	@Override
	public String name() {
		return "LeadBolt";
	}

	@Override
	public View load(Context context, String... keywords) {
		// this is the controller
		if(adController == null) adController = new AdController((Activity) context, pubId, this); 
		if(adView == null) adView = new MockView(context);
		
		return adView;
	}

	@Override
	public IAdNetwork showAd(Callback callback) {
		this.callback = callback;
		adController.resumeAd();
		adController.loadAd();
		return this;
	}

	@Override
	public IAdNetwork unload() {
		adController.pauseAd();
		adController.destroyAd();
		adController = null;
		adView = null;
		return this;
	}


	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MockView
	 * @author Ricky
	 *
	 */
	private static class MockView extends LinearLayout{

		public MockView(Context context) {
			super(context);
			setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 55));
			setMinimumHeight(55);
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void onAdFailed() {
		callback.onAdReceived(this, Result.Bad.message("Ad Failed. AdShowStatus = " + adController.adShowStatus()));
	}

	@Override
	public void onAdLoaded() {
		callback.onAdReceived(this, Result.Good);
	}

	@Override
	public void onAdAlreadyCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdPaused() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdProgress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdResumed() {
		// TODO Auto-generated method stub
		
	}
}

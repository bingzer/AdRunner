/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.lqlsdlqdipwefhnsapz.AdController;
import com.lqlsdlqdipwefhnsapz.AdListener;

@SuppressWarnings("UnusedDeclaration")
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
		callback.onAdReceived(this, AdResult.Bad.message("Ad Failed. AdShowStatus = " + adController.adShowStatus()));
	}

	@Override
	public void onAdLoaded() {
		callback.onAdReceived(this, AdResult.Good);
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

    @Override
    public void onAdCached() {

    }
}

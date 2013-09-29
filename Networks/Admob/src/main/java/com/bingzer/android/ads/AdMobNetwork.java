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

@SuppressWarnings("UnusedDeclaration")
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
		callback.onAdReceived(this, AdResult.Bad.message(err.name()));
	}

	@Override
	public void onReceiveAd(Ad ad) {
		callback.onAdReceived(this, AdResult.Good);
	}

}

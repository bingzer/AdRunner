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

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.banner.AdView;

import android.content.Context;
import android.view.View;

@SuppressWarnings("UnusedDeclaration")
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
		callback.onAdReceived(this, AdResult.Good);
	}

	@Override
	public void noAdFound() {
		callback.onAdReceived(this, AdResult.Bad.message("No Ad found"));
	}

}

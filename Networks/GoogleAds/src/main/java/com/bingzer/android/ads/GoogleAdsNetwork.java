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

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.plus.model.people.Person;

/**
 * AdMobSdkNetwork. This is the old way of doing things
 */
@SuppressWarnings("UnusedDeclaration")
public class GoogleAdsNetwork extends AbsAdNetwork<AdView> {

    public static final int BANNER = 0;
    public static final int FULL_BANNER = 1;
    public static final int LEADERBOARD = 2;
    public static final int MEDIUM_RECTANGLE = 3;
    public static final int WIDE_SKYSCRAPER = 4;
    public static final int SMART_BANNER = 5;

    private AdRequest adRequest;
    private AdSize adSize;

    public GoogleAdsNetwork(int size, String pubId){
        this.pubId = pubId;
        switch (size){
            case BANNER:
                adSize = AdSize.BANNER; break;
            case FULL_BANNER:
                adSize = AdSize.FULL_BANNER; break;
            case LEADERBOARD:
                adSize = AdSize.LEADERBOARD; break;
            case MEDIUM_RECTANGLE:
                adSize = AdSize.MEDIUM_RECTANGLE; break;
            default:
            case SMART_BANNER:
                adSize = AdSize.SMART_BANNER; break;
            case WIDE_SKYSCRAPER:
                adSize = AdSize.WIDE_SKYSCRAPER; break;
        }
    }

    public GoogleAdsNetwork(AdSize adSize, String pubId){
        this.pubId = pubId;
        this.adSize = adSize;
    }

    @Override
    public View getView(){
        return adView;
    }

    @Override
    public String getName() {
        return "AdMob";
    }

    @Override
    public View load(Context context, String... keywords) {
        if(adView == null){
            // -- depending on the screen size..
            adView = new AdView(context);
            adView.setAdListener(createAdListener());
            adView.setLayoutParams(AdContainer.params());
            adView.setAdUnitId(pubId);
            adView.setAdSize(adSize);
        }

        AdRequest.Builder requestBuilder =
                new AdRequest.Builder()
                        .setGender(Helper.getRandom(Person.Gender.FEMALE, Person.Gender.MALE));
        // load all keywords
        if(keywords != null){
            for(String keyword : keywords){
                requestBuilder.addKeyword(keyword);
            }
        }

        // test devices
        for(String device : TestDevices.getDeviceSet()){
            requestBuilder.addTestDevice(device);
        }

        adRequest = requestBuilder.build();

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

    protected final AdListener createAdListener() {
        return new AdListener() {

            @Override
            public void onAdClosed() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                callback.onAdReceived(GoogleAdsNetwork.this, AdResult.Bad.message("Failed to load. ErrorCode: " + errorCode));
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                callback.onAdReceived(GoogleAdsNetwork.this, AdResult.Good);
            }
        };
    }


}

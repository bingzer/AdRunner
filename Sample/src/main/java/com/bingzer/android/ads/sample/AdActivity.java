/**
 * Copyright 2014 Ricky Tobing
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
package com.bingzer.android.ads.sample;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bingzer.android.ads.AdContainer;
import com.bingzer.android.ads.AdMediator;
import com.bingzer.android.ads.AdNetworkList;
import com.bingzer.android.ads.AdResult;
import com.bingzer.android.ads.AmazonAdNetwork;
import com.bingzer.android.ads.GoogleAdsNetwork;
import com.bingzer.android.ads.IAdClient;
import com.bingzer.android.ads.IAdNetwork;
import com.bingzer.android.ads.IAdRunner;
import com.bingzer.android.ads.LeadBoltNetwork;
import com.bingzer.android.ads.MobFoxNetwork;
import com.bingzer.android.ads.TestDevices;

/**
 * Shows a sample AdRunner in the works.
 */
public class AdActivity extends ActionBarActivity implements IAdClient {

    private IAdRunner adRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        // Add the additional device id here
        TestDevices.addAdditionalDevice("E2527975F7968CF4B8BE1BB427FFD7FA");

        // Create the adRunner here
        adRunner = AdMediator.instance().create(this);

        // sets all options you have
        // remote distro, this way you can manage the distribution of network range
        // via the interweb
        adRunner.setDistroUrl("https://raw.github.com/bingzer/AdRunner/master/ads_distro.json");
        // min sleep = 10 max = 30
        // means, the ad network will be switch every 10-30 seconds
        adRunner.setMinSleep(10);
        adRunner.setMaxSleep(30);
        // determine how many network should be switched
        adRunner.setNetworkSwitchCount(10);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // must put onResume here
        // so the adRunner can resume showing the ad
        adRunner.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // must put onPause here
        // so the adRunner can pause showing the ad
        adRunner.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /////////////////////////////////////////////////////////

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public AdContainer getAdContainer() {
        return (AdContainer) findViewById(R.id.adContainer);
    }

    @Override
    public String[] getKeywords() {
        return new String[0];
    }

    @Override
    public AdNetworkList getAdNetworkList() {
        throw new UnsupportedOperationException("getAdNetworkList() not implemented. You need to set this up. Uncomment the code");

        /*
        AdNetworkList list = new AdNetworkList();

        // IMPORTANT: Use your own pub-id to test.
        // For example: If you want AdMob to show about 50% at a time and Amazon: 30%
        // and LeadBolt 25% and MobFox: 25%.
        // This is how you do it.

        list.add(new GoogleAdsNetwork(GoogleAdsNetwork.SMART_BANNER, "<pub-id>"), 50); // 50%
        list.add(new AmazonAdNetwork(AmazonAdNetwork.SIZE_AUTO, "<pub-id>"), 30);  // 30%
        list.add(new LeadBoltNetwork("<pub-id>"), 25); // 15%
        list.add(new MobFoxNetwork("<pub-id>"), 25); // 5%

        return list;
        */
    }

    @Override
    public boolean requestingAd() {
        return true;
    }

    @Override
    public void onAdReceived(IAdNetwork network, AdResult adResult) {
        Log.i("AdActivity", "AdReceived Network " + network.getName() + ". Result = " + adResult.message());
    }
}

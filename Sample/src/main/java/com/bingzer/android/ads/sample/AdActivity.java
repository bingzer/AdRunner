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

public class AdActivity extends ActionBarActivity implements IAdClient {

    private IAdRunner adRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

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
        AdNetworkList list = new AdNetworkList();
        list.add(new GoogleAdsNetwork(GoogleAdsNetwork.SMART_BANNER, ""), 50);
        list.add(new AmazonAdNetwork(AmazonAdNetwork.SIZE_AUTO, ""), 50);

        // To test leadbolt you need to create/have an existing app in LeadBolt Network
        //list.add(new LeadBoltNetwork(""), 25);

        // same thing with mobfox
        //list.add(new MobFoxNetwork(""), 25);


        return list;
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

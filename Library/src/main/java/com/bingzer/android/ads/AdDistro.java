package com.bingzer.android.ads;

import android.util.Log;

import com.bingzer.android.ads.utils.IOUtils;
import com.bingzer.android.ads.utils.Timespan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Ad distro.
 * Instead of hard-coding the range or turn on/off each network,
 * this class should go online somewhere (your web server)
 * grab the ad_distro.json and create the network list
 * based on that.
 */
class AdDistro implements IAdDistro{
    static final String TAG = "AdDistro";
    static final String FILENAME = "ads_distro.json";

    private String url = null;
    private IAdClient adClient;

    ////////////////////////////////////////////////////////////////////////

    AdDistro(IAdClient adClient){
        this.adClient = adClient;
    }

    ////////////////////////////////////////////////////////////////////////

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void configure(AdNetworkList list) {
        // configure according to the json distro configuration
        if(url != null && applyConfiguration(list)){
            Log.i(TAG, "AdDistro configuration is applied");
        }
        else{
            Log.i(TAG, "Not applying AdDistro.");
        }
    }

    ////////////////////////////////////////////////////////////////////////

    private boolean applyConfiguration(AdNetworkList list){
        boolean applied = false;
        try{
            File localJson = new File(adClient.getContext().getCacheDir(), FILENAME);
            // every week, we go out and get the new json file
            if(!localJson.exists() || localJson.lastModified() < Timespan.now() - Timespan.WEEKS_1){
                Log.i(TAG, "Local ads_distro.json does not exists or it may be outdated");
                httpGetDistroJson(localJson);
            }

            BufferedInputStream input = new BufferedInputStream(new FileInputStream(localJson));
            CharSequence json = readInputStream(input);

            list.resetHitPercentages();
            JSONObject rootObject = new JSONObject(json.toString());
            JSONArray jsonNetworks = rootObject.getJSONArray("networks");
            for(int i = 0; i < jsonNetworks.length(); i++){
                JSONObject jsonNetwork = jsonNetworks.getJSONObject(i);

                String name = jsonNetwork.getString("name");
                boolean enabled = jsonNetwork.getBoolean("enabled");
                float hitPercentage = (float)jsonNetwork.getDouble("hit");

                // apply this configuration to the corresponding network
                // from the network list
                IAdNetwork adNetwork = list.getNetworkByName(name);
                if(adNetwork != null){
                    adNetwork.setEnabled(enabled);
                    // only set the hit percentage
                    if(adNetwork.isEnabled()) list.setHitPercentage(adNetwork, hitPercentage);
                }
            }

            applied = true;
        }
        catch (Exception e){
            Log.e(TAG, e.toString());
        }

        return applied;
    }

    private void httpGetDistroJson(File file){
        try{
            URL xurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) xurl.openConnection();
            IOUtils.copyFile(conn.getInputStream(), file);
            conn.disconnect();
        }
        catch (Throwable e){
            Log.e(TAG, "httpGetDistroJson", e);
        }
    }

    private CharSequence readInputStream(InputStream input) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(input));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        r.close();

        return total;
    }

}

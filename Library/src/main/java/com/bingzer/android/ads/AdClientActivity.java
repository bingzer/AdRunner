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
import android.os.Bundle;

/**
 * Activity add ads
 * Created by Ricky Tobing on 7/11/13.
 */
public abstract class AdClientActivity extends Activity implements IAdClient{

    protected IAdRunner adRunner;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        adRunner = AdMediator.instance().create(this);
    }

    /**
     * Returns 'context'
     *
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Returns the ad container.
     *
     * @return
     */
    @Override public abstract AdContainer getAdContainer();

    /**
     * Returns keywords
     *
     * @return
     */
    @Override
    public String[] getKeywords() {
        return new String[0];
    }

    /**
     * Returns all available networks
     *
     * @return
     */
    @Override
    public abstract  AdNetworkList getAdNetworkList();

    /**
     * True if client would like to display ad
     *
     * @return
     */
    @Override
    public boolean requestingAd() {
        return false;
    }

    @Override
    public void onAdReceived(IAdNetwork network, Result result) {

    }
}

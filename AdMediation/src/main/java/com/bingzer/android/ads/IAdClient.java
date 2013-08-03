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

/**
 * Represents an Ad client.
 * This should be implemented in inside an activity
 * where you would like to display the ad
 *
 * @author Ricky Tobing
 */
public interface IAdClient extends IAdNetwork.Callback {

    /**
     * Returns 'context'
     *
     * @return
     */
    Context getContext();

    /**
     * Returns the ad container.
     *
     * @return
     */
    AdContainer getAdContainer();

    /**
     * Returns keywords
     *
     * @return
     */
    String[] getKeywords();

    /**
     * Returns all available networks
     *
     * @return
     */
    AdNetworkList getAdNetworkList();

    /**
     * <code>task</code> should be ran inside
     * the UI Thread
     *
     * @param task
     */
    void runOnUiThread(Runnable task);

    /**
     * True if client would like to display ad
     *
     * @return
     */
    boolean requestingAd();

}

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

import java.util.LinkedList;
import java.util.List;

public class AdNetworkList {

    private final List<IAdNetwork> list = new LinkedList<IAdNetwork>();
    private float nextPoint = 0;

    public AdNetworkList() {
        nextPoint = 0;
    }

    public IAdNetwork add(IAdNetwork network, float hitPercentage) {
        if (network == null) return network;
        if (!hasNetwork(network)) {
            // -- calculate range..
            Range range = new Range();
            range.minimum = nextPoint;
            range.maximum = (range.minimum + hitPercentage) - 1;
            nextPoint = range.maximum + 1;
            network.range(range);
            // -- add to the list
            list.add(network);
        }

        return network;
    }

    /**
     * Returns the next 'random' <code>enabled</code> provider
     *
     * @return
     */
    public IAdNetwork getNextNetwork() {
        float hitPoint = Helper.getRandom(0f, 100f);
        for (IAdNetwork network : list) {
            if (network.enabled() && network.range().inRange(hitPoint))
                return network;
        }

        try {
            return list.get(0);
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * Trus if has this network/network has been added
     *
     * @param network
     * @return
     */
    public boolean hasNetwork(IAdNetwork network) {
        for (IAdNetwork n : list) {
            if (n.name().equalsIgnoreCase(network.name()))
                return true;
        }
        return false;
    }
}

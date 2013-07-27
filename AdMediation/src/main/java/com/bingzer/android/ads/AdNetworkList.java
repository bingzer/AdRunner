package com.bingzer.android.ads;

import java.util.LinkedList;
import java.util.List;

import com.bingzer.android.Helper;
import com.bingzer.android.Range;

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

package com.bingzer.android.ads;

/**
 * Implement distro rule to the AdNetworkList
 */
public interface IAdDistro {

    void setUrl(String url);

    /**
     * This should be called in non-block thread.
     * Because we may go out and check data from the Interweb
     */
    void configure(AdNetworkList list);

}

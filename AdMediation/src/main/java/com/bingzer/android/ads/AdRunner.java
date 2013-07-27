package com.bingzer.android.ads;

import android.view.View;

class AdRunner implements IAdRunner {

    private String name;
    private IAdClient client;
    private AdMediator mediator;
    private IAdNetwork currentNetwork;

    private int failCounter = 0;
    private int networkSwitchCount = 0;
    private int networkSwitchCounter = DEFAULT_NETWORK_SWITCH_COUNT;
    private int minSleep = MIN_SLEEP_TIME;  // in seconds
    private int maxSleep = MAX_SLEEP_TIME; // in seconds
    private boolean die = false;
    private Thread thread;

    AdRunner(AdMediator mediator, IAdClient client) {
        this.mediator = mediator;
        this.client = client;
        this.name = client.getClass().getSimpleName();

        // randomize..
        mediator.log("********** Starting AdRunner: %1$s. **************", name);
    }

    public void setNetworkSwitchCount(int switchCount) {
        this.networkSwitchCount = switchCount;
    }

    public int getNetworkSwitchCount() {
        return networkSwitchCount;
    }

    public void setMinSleep(int seconds) {
        this.minSleep = seconds;
    }

    public void setMaxSleep(int seconds) {
        this.maxSleep = seconds;
    }

    @Override
    public void onResume() {
        mediator.log("%s onResume()", name);
        if (thread == null) {
            die = false;

            thread = createThread();
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.setName("AdRunner.Thread{" + client.getClass().getSimpleName() + "}");
            thread.setUncaughtExceptionHandler(mediator);
            thread.start();
        } else {
            nextAd();
        }
    }

    @Override
    public void onPause() {
        mediator.log("%s onPause()", name);
        die = true;
        interrupt();
    }

    public void nextAd() {
        if (isAlive()) interrupt();
    }

    @Override
    public void onAdReceived(IAdNetwork network, Result result) {
        if (!result.success()) {
            failCounter++;
            mediator.log("onAdReceived() - Failed. %1$s - %2$s", network.name(), result.message() == null ? "" : result.message());
            // this is to force to switch network..
            networkSwitchCount = networkSwitchCounter;
            nextAd();
        } else {
            failCounter = 0;
            mediator.log("onAdReceived() - Success. %1$s - %2$s", network.name(), result.message() == null ? "" : result.message());
        }

        // pass the information to the client
        client.onAdReceived(network, result);
    }

    private boolean isAlive() {
        if (thread != null)
            return thread.isAlive();
        return false;
    }

    private void interrupt() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    /**
     * Creates the thread
     *
     * @return
     */
    private final Thread createThread() {
        return new Thread(new Runnable() {
            private int sleep;

            @Override
            public void run() {
                while (!die) {
                    if (failCounter >= AdMediator.MAX_FAIL_COUNT) {
                        mediator.log("Too many time failed. Exiting...");
                        break;
                    }

                    // established the sleep timer
                    sleep = Helper.getRandom(minSleep, maxSleep) * 1000;
                    mediator.log("%1$s AdNetwork: Schedule sleep-time: %2$s ms", name, sleep);
                    // see if we should switch network
                    if (shouldSwitchNetwork()) {

                        // #1. -- unload the previous network (if any)
                        if (currentNetwork != null) {
                            client.runOnUiThread(new Runnable() {
                                public void run() {
                                    client.getAdContainer().removeAllViews();
                                    currentNetwork.unload();
                                    mediator.log("%1$s AdNetwork: %2$s unload()", name, currentNetwork.name());
                                }
                            });
                        }

                        // #2. -- Load and prep the next the network
                        client.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                currentNetwork = client.getAdNetworkList().getNextNetwork();

                                View adView = currentNetwork.load(client.getContext(), client.getKeywords());
                                mediator.log("%1$s AdNetwork: %2$s load()", name, currentNetwork.name());
                                client.getAdContainer().addView(adView, AdContainer.params());

                                mediator.log("%1$s AdNetwork: %2$s showAd()", name, currentNetwork.name());
                                currentNetwork.showAd(AdRunner.this);
                            }
                        });
                    }
                    // -- instead of switch network
                    // -- we should show/load next ad
                    else if (currentNetwork != null) {
                        client.runOnUiThread(new Runnable() {
                            public void run() {
                                mediator.log("%1$s AdNetwork: %2$s showAd()", name, currentNetwork.name());
                                currentNetwork.showAd(AdRunner.this);
                            }
                        });
                    }


                    // ---------------------------------- //
                    // ------------- sleep -------------- //
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        mediator.log("%1$s Interrupted!", name);
                    }
                }// end while

                die = true;
                thread = null;
            }
        });
    }

    private boolean shouldSwitchNetwork() {
        if (currentNetwork == null) return true;

        networkSwitchCounter++;
        if (networkSwitchCounter > networkSwitchCount) {
            networkSwitchCounter = 0;
            return true;
        }

        return false;

    }
}

package com.bingzer.android.ads;

import java.util.LinkedList;
import java.util.List;

import com.bingzer.android.Result;
import com.bingzer.android.app.AndroidApp;

public class AdMediator implements Thread.UncaughtExceptionHandler {
    public static final int MAX_FAIL_COUNT = 5;

    private static AdMediator mediator;

    public static AdMediator instance() {
        if (mediator == null)
            mediator = new AdMediator();
        return mediator;
    }

    private AdMediator() {
        // restricted
    }


    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    private final List<String> keywordList = new LinkedList<String>();

    /**
     * This should be called in onCreate()
     *
     * @param client
     * @return
     */
    public IAdRunner create(IAdClient client) {
        keywordList.clear();
        if (client.requestingAd()) {
            return new AdRunner(this, client);
        } else {
            return new DummyRunner();
        }
    }

    /**
     * Adds a global keywords
     *
     * @param keywords
     */
    public void addKeywords(String... keywords) {
        if (keywords != null) {
            for (int i = 0; i < keywords.length; i++) {
                if (!keywordList.contains(keywords[i])) {
                    keywordList.add(keywords[i]);
                }
            }
        }
    }

    /**
     * Clear keywords
     */
    public void clearKeywords() {
        // clear keywords..
        keywordList.clear();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        AndroidApp.e("Uncaught Exception from " + thread.getName(), ex);
    }


    /////////////////////////////////////////////////////////////////////////
    void log(String format, Object... args) {
        AndroidApp.l("AdMediator: " + String.format(format, args));
    }


    /**
     * Dummy runner
     *
     * @author 11856
     */
    static class DummyRunner implements IAdRunner {

        @Override
        public void onResume() {
            // do nothing
        }

        @Override
        public void onPause() {
            // do nothing
        }

        @Override
        public void onAdReceived(IAdNetwork network, Result result) {
            // do nothing
        }

        @Override
        public void setNetworkSwitchCount(int switchCount) {
            // TODO Auto-generated method stub

        }

        @Override
        public void setMinSleep(int seconds) {
            // TODO Auto-generated method stub

        }

        @Override
        public void setMaxSleep(int seconds) {
            // TODO Auto-generated method stub

        }

    }
}

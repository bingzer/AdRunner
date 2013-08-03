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

public class AdMediator implements Thread.UncaughtExceptionHandler {
    static final String TAG = "AdMediator";

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
        android.util.Log.e(TAG, "Uncaught Exception from " + thread.getName(), ex);
    }


    /////////////////////////////////////////////////////////////////////////
    void log(String format, Object... args) {
        android.util.Log.i(TAG, "AdMediator: " + String.format(format, args));
    }


    /**
     * Dummy runner
     *
     * @author Ricky Tobing
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

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
import android.view.View;

/**
 * Represents an ad network
 *
 * @author Ricky Tobing
 */

public interface IAdNetwork {

    IAdNetwork enable(boolean enabled);

    /**
     * Returns the name
     *
     * @return the name of this ad network
     */
    String name();

    /**
     * True if enabled
     *
     * @return
     */
    boolean enabled();

    /**
     * Sets the range
     *
     * @param range
     * @return
     */
    IAdNetwork range(Range range);

    /**
     * Returns the range
     *
     * @return
     */
    Range range();

    /**
     * Returns the view (this may be null)
     * if {@link #load(android.content.Context, String...)} has not
     * been called yet
     *
     * @return
     */
    View getView();

    /**
     * Loads and return the view
     *
     * @param context
     * @param keywords
     * @return
     */
    View load(Context context, String... keywords);

    /**
     * Show ads
     */
    IAdNetwork showAd(Callback callback);

    /**
     * Unload views
     */
    IAdNetwork unload();

    /**
     * Returns true if this particular network has onBackPressed
     * available.
     */
    boolean onBackPressed();

    /**
     * Callback
     */
    public static interface Callback {

        void onAdReceived(IAdNetwork network, AdResult adResult);

    }
}

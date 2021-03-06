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

    /**
     * Returns the name
     *
     * @return the name of this ad network
     */
    String getName();

    /**
     * Sets enabled
     */
    void setEnabled(boolean enabled);

    /**
     * True if enabled
     */
    boolean isEnabled();

    /**
     * Sets the range
     */
    void setRange(Range range);

    /**
     * Returns the range
     */
    Range getRange();

    /**
     * Returns the view (this may be null)
     * if {@link #load(android.content.Context, String...)} has not
     * been called yet
     */
    View getView();

    /**
     * Loads and return the view
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

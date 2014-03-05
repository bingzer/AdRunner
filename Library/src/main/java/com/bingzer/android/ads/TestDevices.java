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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * List of all test devices that we have
 *
 * @author Ricky
 */
public class TestDevices {

    /**
     * This is the placeholder.
     * Nobody should ever use this except
     * from TestDevices.getDevices()
     */
    private static String[] devices = null;
    private static List<String> additionalDevices = new ArrayList<String>();

    // ------------------------------------------------------------------------------------- //

    /**
     * Emulator
     */
    public static final String TEST_EMULATOR = "EMULATOR"; // AdRequest.TEST_EMULATOR;

    /**
     * Motorola Atrix 2
     */
    public static final String MOTOROLA_ATRIX_2 = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    //public static final String MOTOROLA_ATRIX_2  = "6E1CE07308BB6ABE2920B82699F55FE6";

    /**
     * Asus Nexus 7
     */
    //public static final String NEXUS_7           = "233B658F7FF092B18562B192EDD59331";
    public static final String NEXUS_7 = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    /**
     * Htc aria
     */
    public static final String HTC_ARIA = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    /**
     * Samsung Galaxy S3
     */
    public static final String SAMSUNG_GALAXY_S3 = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    /**
     * Kindle Fire HD
     */
    public static final String KINDLE_FIRE_HD = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";


    // ------------------------------------------------------------------------------------- //

    /**
     * ALL
     */
    public static String[] getDevices() {
        if (devices == null) {
            devices = new String[]{
                    TEST_EMULATOR,
                    MOTOROLA_ATRIX_2,
                    NEXUS_7,
                    HTC_ARIA,
                    SAMSUNG_GALAXY_S3,
                    KINDLE_FIRE_HD
            };
        }

        return devices;
    }

    public static void addAdditionalDevice(String deviceId){
        additionalDevices.add(deviceId);
    }

    /**
     * Returns as a Set of String
     */
    public static Set<String> getDeviceSet() {
        Set<String> deviceSet = new HashSet<String>();
        Collections.addAll(deviceSet, getDevices());
        Collections.addAll(deviceSet, additionalDevices.toArray(new String[additionalDevices.size()]));

        return deviceSet;
    }
}

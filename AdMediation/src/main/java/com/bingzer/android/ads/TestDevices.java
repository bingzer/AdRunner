package com.bingzer.android.ads;

import com.google.ads.AdRequest;

import java.util.HashSet;
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

    // ------------------------------------------------------------------------------------- //

    /**
     * Emulator
     */
    public static final String TEST_EMULATOR = AdRequest.TEST_EMULATOR;

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
    public static final String[] getDevices() {
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

    /**
     * Returns as a Set of String
     *
     * @return
     */
    public static final Set<String> getDeviceSet() {
        Set<String> deviceSet = new HashSet<String>();
        for (String device : getDevices()) {
            deviceSet.add(device);
        }

        return deviceSet;
    }
}

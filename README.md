[![Build Status](https://travis-ci.org/bingzer/AdRunner.svg?branch=master)](https://travis-ci.org/bingzer/AdRunner)

AdRunner
========

Lightweight Ad mediator for Android App. Currently, this library supports AdMob, Amazon-Ad, LeadBolt and Mobfox. This library is only intended to showing a banner type of ads.

It supports remote configuration via a 'distro-url'. See sample project for usage

Download
========
```gradle

    // main component
    compile 'com.bingzer.android.ads:adrunner:0.0.5'
    
    // GoogleAds (was AdMob)
    compile 'com.bingzer.android.ads:adnets-google-ads:0.0.5'
    
    // Amazon
    compile 'com.bingzer.android.ads:adnets-amazon:0.0.5'
    
    // LeadBolt
    compile 'com.bingzer.android.ads:adnets-leadbolt:0.0.5'
    
    // Mobfox
    compile 'com.bingzer.android.ads:adnets-mobfox:0.0.5'
    
```

AppCompat
=========
Some library uses appcompat or support-v4. If conflict is detected, use:
```gradle
    // exclude whatever the leadbolt library has in there
    // and tell it to use the latest appcompat-v7
    compile ('com.bingzer.android.ads:adnets-leadbolt:0.0.5'){
        exclude module: 'support-v4'
        compile 'com.android.support:appcompat-v7:19+'
    }
```

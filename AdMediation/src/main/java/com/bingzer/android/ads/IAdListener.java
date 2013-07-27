package com.bingzer.android.ads;

import com.bingzer.android.Result;

public interface IAdListener {

    void onAdReceived(Result result, IAdNetwork network);

}

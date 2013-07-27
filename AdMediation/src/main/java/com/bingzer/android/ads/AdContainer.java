package com.bingzer.android.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * This class represents and Ad container.
 */
public class AdContainer extends LinearLayout {

    public AdContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setBackgroundResource(android.R.color.transparent);
    }

    /**
     * Default children's params
     *
     * @return
     */
    public static LayoutParams params() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        return params;
    }
}

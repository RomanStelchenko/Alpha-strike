package com.example.raduga.alphastrike;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by gardi on 26.02.2017.
 */
public class DisplayProperties {
    private DisplayMetrics metrics;
    private int width;
    private int height;

    public DisplayProperties() {
        metrics = Resources.getSystem().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    public int getWidth() {
        return  width;
    }

    public int getHeight() {
        return height;
    }
}
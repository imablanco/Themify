package com.ablanco.themify;

import android.support.annotation.FloatRange;

/**
 * Created by Álvaro Blanco Cabrero on 18/09/2017.
 * ThemifySample.
 */

public class ColorUtils {

    public static int applyAlpha(int color, @FloatRange(from = 0, to = 1) float alpha){
        return android.support.v4.graphics.ColorUtils.setAlphaComponent(color, (int) (alpha * 255));
    }
}

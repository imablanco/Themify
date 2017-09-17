package com.ablanco.themify;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.util.TypedValue;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

class ThemeUtils {

    static int getColorPrimary(Context context) {
        return getAttributeColor(context, R.attr.colorPrimary);
    }

    static int getColorPrimaryDark(Context context) {
        return getAttributeColor(context, R.attr.colorPrimaryDark);
    }

    static int getColorAccent(Context context) {
        return getAttributeColor(context, R.attr.colorAccent);
    }

    private static int getAttributeColor(Context context, @AttrRes int attribute) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attribute, typedValue, true);
        return typedValue.data;
    }
}

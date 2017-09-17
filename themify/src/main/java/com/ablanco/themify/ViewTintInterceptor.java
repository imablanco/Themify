package com.ablanco.themify;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

@SuppressWarnings("RestrictedApi")
class ViewTintInterceptor implements Interceptor {

    @Override
    public InflateResult intercept(Chain chain) {
        InflateResult result = chain.proceed(chain.request());
        View view = result.view();

        if (view != null && view.getContext() instanceof Themifable) {
            ColorPalette colorPalette = ((Themifable) view.getContext()).getColorPalette();
            if (view instanceof CheckBox) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((CheckBox) view).setButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{colorPalette.getColorAccent(), Color.GRAY}));
                } else if (view instanceof AppCompatCheckBox) {
                    ((AppCompatCheckBox) view).setSupportButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{colorPalette.getColorAccent(), Color.GRAY}));
                }
            }

            if (view instanceof Toolbar) {
                view.setBackgroundColor(colorPalette.getColorPrimary());
            }
        }

        return result;
    }
}

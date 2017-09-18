package com.ablanco.themify;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

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
            Context context = view.getContext();
            ColorPalette colorPalette = ((Themifable) view.getContext()).getColorPalette();

            // TODO: 19/09/2017 apply light or dark themes
            if (view instanceof CompoundButton) {
                if (view instanceof Switch) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((Switch) view).setTrackTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                                new int[]{colorPalette.getColorAccent(), ContextCompat.getColor(context, R.color.switchTrackNormalMaterialLight)}));
                        ((Switch) view).setThumbTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                                new int[]{colorPalette.getColorAccent(), ContextCompat.getColor(context, R.color.switchThumbNormalMaterialLight)}));

                    }
                } else if (view instanceof SwitchCompat) {
                    ((SwitchCompat) view).setTrackTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{ColorUtils.applyAlpha(colorPalette.getColorAccent(), .26f), // TODO: 19/09/2017 .30 in DARK!! 
                                    ContextCompat.getColor(context, R.color.switchTrackNormalMaterialLight)}));
                    ((SwitchCompat) view).setThumbTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{colorPalette.getColorAccent(), ContextCompat.getColor(context, R.color.switchThumbNormalMaterialLight)}));

                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((CompoundButton) view).setButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{colorPalette.getColorAccent(), ContextCompat.getColor(context, R.color.secondaryTextDefaultMaterialLight)}));
                } else if (view instanceof TintableCompoundButton) {
                    ((TintableCompoundButton) view).setSupportButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{}},
                            new int[]{colorPalette.getColorAccent(), ContextCompat.getColor(context, R.color.secondaryTextDefaultMaterialLight)}));
                }

            }

            if (view instanceof ProgressBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((ProgressBar) view).setIndeterminateTintList(colorPalette.colorStateListFromColorAccent());
                    ((ProgressBar) view).setProgressTintList(colorPalette.colorStateListFromColorAccent());
                    ((ProgressBar) view).setSecondaryProgressTintList(colorPalette.colorStateListFromColorAccent());
                }
            }

            if (view instanceof DrawerLayout) {
                ((DrawerLayout) view).setStatusBarBackgroundColor(colorPalette.getColorPrimaryDark());
            }


        }

        return result;
    }
}

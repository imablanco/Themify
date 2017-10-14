package com.ablanco.themify;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

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

            //if our view has a Ripple bg, tint it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (view.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) view.getBackground()).setColor(colorPalette.colorStateListFromControlHighLight());
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (view.getForeground() instanceof RippleDrawable) {
                    ((RippleDrawable) view.getForeground()).setColor(colorPalette.colorStateListFromControlHighLight());
                }
            }

            // TODO: 19/09/2017 apply light or dark themes
            if (view instanceof CompoundButton) {

                if (view instanceof Switch || view instanceof SwitchCompat) {

                    int trackCheckedColor = view instanceof Switch
                            ? colorPalette.getColorAccent()
                            : ColorUtils.applyAlpha(colorPalette.getColorAccent(), .26f);// TODO: 19/09/2017 .30 in DARK!!
                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked},
                    };

                    int[] thumbColors = new int[]{
                            ContextCompat.getColor(context, R.color.switchThumbNormalMaterialLight),
                            colorPalette.getColorAccent(),
                    };

                    int[] trackColors = new int[]{
                            ContextCompat.getColor(context, R.color.switchTrackNormalMaterialLight),
                            trackCheckedColor,
                    };

                    Drawable thumbDrawable = view instanceof Switch
                            ? ((Switch) view).getThumbDrawable()
                            : ((SwitchCompat) view).getThumbDrawable();
                    Drawable trackDrawable = view instanceof Switch
                            ? ((Switch) view).getTrackDrawable()
                            : ((SwitchCompat) view).getTrackDrawable();

                    DrawableCompat.setTintList(DrawableCompat.wrap(thumbDrawable), new ColorStateList(states, thumbColors));
                    DrawableCompat.setTintList(DrawableCompat.wrap(trackDrawable), new ColorStateList(states, trackColors));


                } else if (view instanceof TintableCompoundButton) {

                    int[][] states = new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked},
                    };

                    int[] colors = new int[]{
                            ContextCompat.getColor(context, R.color.secondaryTextDefaultMaterialLight),
                            colorPalette.getColorAccent(),
                    };

                    ((TintableCompoundButton) view).setSupportButtonTintList(new ColorStateList(states, colors));
                }

            }

            if (view instanceof AppCompatEditText) {
                ((AppCompatEditText) view).setSupportBackgroundTintList(colorPalette.colorStateListFromColorAccent());
                //set text color highlight, that is 40% alpha of accentColor
                ((AppCompatEditText) view).setHighlightColor(ColorUtils.applyAlpha(colorPalette.getColorAccent(), .4f));
                TintUtils.colorTextViewHandles((TextView) view, colorPalette.getColorAccent());
                TintUtils.hideTextCursor((TextView) view);
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

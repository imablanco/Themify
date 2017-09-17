package com.ablanco.themify;

import android.content.Context;
import android.content.res.ColorStateList;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

public class ColorPalette {

    private int colorPrimary;
    private int colorPrimaryDark;
    private int colorAccent;

    static ColorPalette defaultColorPalette(Context context) {
        return new Builder()
                .colorPrimary(ThemeUtils.getColorPrimary(context))
                .colorPrimaryDark(ThemeUtils.getColorPrimaryDark(context))
                .colorAccent(ThemeUtils.getColorAccent(context))
                .build();
    }

    private ColorPalette(Builder builder) {
        this.colorPrimary = builder.colorPrimary;
        this.colorPrimaryDark = builder.colorPrimaryDark;
        this.colorAccent = builder.colorAccent;
    }

    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public void setColorPrimaryDark(int colorPrimaryDark) {
        this.colorPrimaryDark = colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public ColorStateList colorStateListFromColorPrimary() {
        return ColorStateList.valueOf(colorPrimary);
    }

    public ColorStateList colorStateListFromColorPrimaryDark() {
        return ColorStateList.valueOf(colorPrimaryDark);
    }

    public ColorStateList colorStateListFromColorAccent() {
        return ColorStateList.valueOf(colorAccent);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int colorPrimary = 0;
        private int colorPrimaryDark = 0;
        private int colorAccent = 0;

        private Builder() {
        }

        public Builder colorPrimary(int color) {
            colorPrimary = color;
            return this;
        }

        public Builder colorPrimaryDark(int color) {
            colorPrimaryDark = color;
            return this;
        }

        public Builder colorAccent(int color) {
            colorAccent = color;
            return this;
        }

        public Builder fallbackColorPrimary(Context context) {
            colorPrimary = ThemeUtils.getColorPrimary(context);
            return this;
        }

        public Builder fallbackColorPrimaryDark(Context context) {
            colorPrimaryDark = ThemeUtils.getColorPrimaryDark(context);
            return this;
        }

        public Builder fallbackColorAccent(Context context) {
            colorAccent = ThemeUtils.getColorAccent(context);
            return this;
        }

        public ColorPalette build() {
            return new ColorPalette(this);
        }
    }
}

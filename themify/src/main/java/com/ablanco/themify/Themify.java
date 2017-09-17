package com.ablanco.themify;

import android.support.annotation.NonNull;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

public class Themify {

    private ColorPalette mColorPalette;

    private static Themify sInstance;

    private Themify(Builder builder) {
        mColorPalette = builder.colorPalette;
        ViewPump.init(ViewPump.builder().addInterceptor(new ViewTintInterceptor()).build());
    }

    public static void init(Themify themify) {
        sInstance = themify;
    }

    static Themify get() {
        return sInstance;
    }

    ColorPalette getColorPalette() {
        return mColorPalette;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ColorPalette colorPalette;

        private Builder() {
        }

        public Builder colorPalette(@NonNull ColorPalette colorPalette) {
            this.colorPalette = colorPalette;
            return this;
        }

        public Themify build() {
            return new Themify(this);
        }
    }
}

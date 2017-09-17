package com.ablanco.themify;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

public abstract class ThemifyActivity extends AppCompatActivity implements Themifable {

    private ColorPalette colorPalette;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        colorPalette = getLocalColorPalette();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getColorPalette().getColorPrimaryDark());
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setBackgroundDrawable(DrawableUtils.colorDrawable(getColorPalette().getColorPrimary()));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ThemifyContextWrapper.wrap(newBase));
    }

    /**
     * Create {@link ColorPalette} to tint views inflated by this Activity
     *
     * @return an instance of {@link ColorPalette} of null to fallback on other ones
     */
    @Nullable
    public abstract ColorPalette getLocalColorPalette();

    /**
     * Search for the correct {@link ColorPalette} to tint views.
     * Search order is:
     * 1 - returns local {@link ColorPalette} if declared.
     * 2 - returns global {@link ColorPalette} if declared.
     * 3 - returns default {@link ColorPalette} that has the same colors as Activity's theme
     *
     * @return the correct {@link ColorPalette} to tint views.
     */
    @NonNull
    @Override
    public final ColorPalette getColorPalette() {
        if (colorPalette != null) return colorPalette;
        if (Themify.get().getColorPalette() != null) return Themify.get().getColorPalette();
        return ColorPalette.defaultColorPalette(this);
    }
}

package com.ablanco.themify.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ablanco.themify.ColorPalette;
import com.ablanco.themify.ThemifyActivity;

public class MainActivity extends ThemifyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Nullable
    @Override
    public ColorPalette getLocalColorPalette() {
        return null;
    }
}

package com.ablanco.themify.sample;

import android.app.Application;
import android.graphics.Color;

import com.ablanco.themify.ColorPalette;
import com.ablanco.themify.Themify;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Themify.init(Themify.builder().colorPalette(ColorPalette.builder()
                .colorAccent(Color.RED)
                .colorPrimary(Color.GREEN)
                .colorPrimaryDark(Color.CYAN)
                .build()).build());
    }
}

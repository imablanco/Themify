package com.ablanco.themify;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;

/**
 * Created by Álvaro Blanco Cabrero on 17/09/2017.
 * ThemifySample.
 */

public class ThemifyContextWrapper extends ViewPumpContextWrapper {

    private ThemifyContextWrapper(Context base) {
        super(base);
    }

    public static ContextWrapper wrap(@NonNull Context base) {
        return new ThemifyContextWrapper(base);
    }
}

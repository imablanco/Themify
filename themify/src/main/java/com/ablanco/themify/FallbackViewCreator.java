package com.ablanco.themify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

interface FallbackViewCreator {
    @Nullable
    View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @Nullable AttributeSet attrs);
}

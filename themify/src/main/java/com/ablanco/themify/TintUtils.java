package com.ablanco.themify;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Álvaro Blanco Cabrero on 15/10/2017.
 * ThemifySample.
 */

public class TintUtils {

    /**
     * Set the color of the handles when you select text in a
     * {@link android.widget.EditText} or other view that extends {@link TextView}.
     *
     * @param view  The {@link TextView} or a {@link View} that extends {@link TextView}.
     * @param color The color to set for the text handles
     */
    public static void colorTextViewHandles(TextView view, int color) {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            if (!editorField.isAccessible()) {
                editorField.setAccessible(true);
            }

            Object editor = editorField.get(view);
            Class<?> editorClass = editor.getClass();

            String[] handleNames = {"mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter"};
            String[] resNames = {"mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes"};

            for (int i = 0; i < handleNames.length; i++) {
                Field handleField = editorClass.getDeclaredField(handleNames[i]);
                if (!handleField.isAccessible()) {
                    handleField.setAccessible(true);
                }

                Drawable handleDrawable = (Drawable) handleField.get(editor);

                if (handleDrawable == null) {
                    Field resField = TextView.class.getDeclaredField(resNames[i]);
                    if (!resField.isAccessible()) {
                        resField.setAccessible(true);
                    }
                    int resId = resField.getInt(view);
                    handleDrawable = ContextCompat.getDrawable(view.getContext(), resId);
                }

                if (handleDrawable != null) {
                    Drawable drawable = handleDrawable.mutate();
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    handleField.set(editor, drawable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideTextCursor(TextView view) {
        //Hide tinted cursor. Because there is no way to do it from code, we need
        //to rely on reflection to do it, and just setting it as @null, not even tinting it :(
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(view, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

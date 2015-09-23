package com.rajasharan.widget;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/**
 * Copied from RectEvaluator (API 21)
 */
public class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    public RectEvaluator(Rect reuse) {
        if (reuse != null) {
            mRect = reuse;
        }
        else {
            mRect = new Rect();
        }
    }

    @Override
    public Rect evaluate(float fraction, Rect startValue, Rect endValue) {
        /* Copied from RectEvaluator (API 21) */
        int left = startValue.left + (int) ((endValue.left - startValue.left) * fraction);
        int top = startValue.top + (int) ((endValue.top - startValue.top) * fraction);
        int right = startValue.right + (int) ((endValue.right - startValue.right) * fraction);
        int bottom = startValue.bottom + (int) ((endValue.bottom - startValue.bottom) * fraction);

        mRect.set(left, top, right, bottom);
        return mRect;
    }
}

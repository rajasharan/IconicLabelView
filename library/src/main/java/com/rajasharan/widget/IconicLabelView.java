package com.rajasharan.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rajasharan on 9/21/15.
 */
public class IconicLabelView extends View implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    private static final String TAG = "IconicLabelView";

    private Drawable mIcon;
    private TextPaint mTextPaint;
    private String mText1;
    private String mText2;
    private Rect mTextBounds;
    private String mText;
    private ValueAnimator mAnim;
    private float[] mSpacing;
    private Paint mBackgroudPaint;
    private RectF mRoundedRect;
    private float mRoundedRadius;
    private boolean mAnimText1ToText2;

    public IconicLabelView(Context context) {
        this(context, null, 0);
    }

    public IconicLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconicLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mSpacing = new float[1];
        mTextPaint.getTextWidths(new char[]{' '}, 0, 1, mSpacing);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconicLabelView);
        setIcon(a.getDrawable(R.styleable.IconicLabelView_label_icon));
        setBackground(a.getColor(R.styleable.IconicLabelView_bg_color, -1));
        setCornerRadius(a.getFloat(R.styleable.IconicLabelView_corner_radius, 5.0f));
        setTextColor(a.getColor(R.styleable.IconicLabelView_text_color, -1));
        setTextSize(a.getDimensionPixelSize(R.styleable.IconicLabelView_text_size, 14));
        setTexts(a.getString(R.styleable.IconicLabelView_text1), a.getString(R.styleable.IconicLabelView_text2));
        a.recycle();
    }

    public IconicLabelView setIcon(Drawable d) {
        mIcon = d;
        return this;
    }

    public IconicLabelView setTexts(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return this;
        }
        mText1 = text1;
        Rect text1Bounds = new Rect();
        mTextPaint.getTextBounds(mText1, 0, mText1.length(), text1Bounds);

        mText2 = text2;
        Rect text2Bounds = new Rect();
        mTextPaint.getTextBounds(mText2, 0, mText2.length(), text2Bounds);

        mText = text1;
        mTextBounds = new Rect(text1Bounds);

        mAnimText1ToText2 = true;
        mAnim = ValueAnimator.ofObject(new RectEvaluator(mTextBounds), text1Bounds, text2Bounds);
        mAnim.addUpdateListener(this);
        mAnim.addListener(this);
        return this;
    }

    public IconicLabelView setBackground(int color) {
        if (color == -1) {
            return this;
        }
        mBackgroudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroudPaint.setStyle(Paint.Style.FILL);
        mBackgroudPaint.setColor(color);

        mRoundedRect = new RectF();
        return this;
    }

    public IconicLabelView setCornerRadius(float radius) {
        mRoundedRadius = radius;
        return this;
    }

    public IconicLabelView setTextColor(int color) {
        if (color == -1) {
            return this;
        }
        mTextPaint.setColor(color);
        return this;
    }

    /**
     * Do not call this method after text values are set.
     * Call before setting text values because text bounds are
     * computed based on textSize
     *
     * @param raw size in raw pixels
     * @return
     */
    public IconicLabelView setTextSize(float raw) {
        mTextPaint.setTextSize(raw);
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mTextBounds == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int w = MeasureSpec.makeMeasureSpec(mTextBounds.width() + mTextBounds.height() + 2 * (int) mSpacing[0], MeasureSpec.EXACTLY);
        int h = MeasureSpec.makeMeasureSpec(mTextBounds.height() + 2*(int)mSpacing[0], MeasureSpec.EXACTLY);
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int l = 0;
        int t = 0;
        int r = l + getWidth();
        int b = t + getHeight();
        int iconSize = mTextBounds.height();
        int spacing = (int)mSpacing[0];

        drawRoundedBackground(canvas, l, t, r, b);
        drawIcon(canvas, l, t + spacing, iconSize);
        drawText(canvas, l - mTextBounds.left + iconSize + spacing, t - mTextBounds.top + spacing);
    }

    private void drawIcon(Canvas canvas, int l, int t, int size) {
        if (mIcon != null) {
            mIcon.setBounds(l, t, l + size, t + size);
            mIcon.draw(canvas);
        }
    }

    private void drawText(Canvas canvas, int l, int t) {
        canvas.drawText(mText, l, t, mTextPaint);
    }

    private void drawRoundedBackground(Canvas canvas, int l, int t, int r, int b) {
        if (mBackgroudPaint != null) {
            mRoundedRect.set(l, t, r, b);
            canvas.drawRoundRect(mRoundedRect, mRoundedRadius, mRoundedRadius, mBackgroudPaint);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        requestLayout();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (mText1.length() <= mText2.length()) {
            if (mAnimText1ToText2) {
                mText = mText2;
            } else if (!mAnimText1ToText2) {
                //do nothing - let onAnimationEnd take care of this branch
            }
        }
        else if (mText2.length() < mText1.length()) {
            if (!mAnimText1ToText2) {
                mText = mText1;
            } else if (mAnimText1ToText2) {
                //do nothing - let onAnimationEnd take care of this branch
            }
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mText1.length() >= mText2.length()) {
            if (mAnimText1ToText2) {
                mText = mText2;
            } else if (!mAnimText1ToText2) {
                //do nothing - onAnimationStart took care of this branch
            }
        }
        else if (mText2.length() > mText1.length()) {
            if (!mAnimText1ToText2) {
                mText = mText1;
            } else if (mAnimText1ToText2) {
                //do nothing - onAnimationStart took care of this branch
            }
        }
        mAnimText1ToText2 = !mAnimText1ToText2;
        invalidate();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!mAnim.isRunning()) {
                    if (mAnimText1ToText2) {
                        mAnim.start();
                    }
                    else if (!mAnimText1ToText2) {
                        mAnim.reverse();
                    }
                    return true;
                }
        }
        return false;
    }
}

package com.xxm.mmd.component_news;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.Timer;

/**
 * TODO: document your custom view class.
 */
public class HzJView extends View {
    private String mText;
    private int mCircleColor = Color.RED;
    private float mArcWidth = 5;


    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private float mTextSize = 0;

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private float mCircleX;
    private float mCircleY;
    private float mRadius;
    private ValueAnimator animator;
    private float mStartAngle = 0;
    private float mSweepAngle = 260;

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidateTextPaintAndMeasurements();
    }

    private int mTextColor = Color.RED;


    public HzJView(Context context) {
        super(context);
        init(null, 0);
    }

    public HzJView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public HzJView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.HzJView, defStyle, 0);

        mText = a.getString(
                R.styleable.HzJView_HzText);
        mCircleColor = a.getColor(
                R.styleable.HzJView_HzArcColor,
                mCircleColor);
        mCircleColor = a.getColor(
                R.styleable.HzJView_HzArcColor,
                mCircleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mArcWidth = a.getDimension(
                R.styleable.HzJView_HzArcWidth,
                mArcWidth);
        mTextSize = a.getDimension(
                R.styleable.HzJView_HzTextSize,
                mTextSize);

        mTextColor = a.getColor(
                R.styleable.HzJView_HzTextColor
        ,mTextColor);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
        invalidateCirclePaintAndMeasurements();

        initAnimation();
    }

    private void initAnimation() {
        animator = ObjectAnimator.ofInt(0,360);
        animator.setDuration(5000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                setArcTop(value);
            }
        });

    }

    private void setArcTop(int value){
        mStartAngle = value;
        invalidate();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextWidth = mTextPaint.measureText(mText);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }
    private void invalidateCirclePaintAndMeasurements() {
        mCirclePaint.setColor(mCircleColor);
        mArcPaint.setColor(mCircleColor);
        mArcPaint.setStrokeWidth(mArcWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        drawCircle(canvas);

        drawText(canvas);

        drawArc(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(animator.isRunning()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if(animator.isPaused()){
                        animator.resume();
                    }else {
                        animator.pause();
                    }
                }else {
                    animator.end();
                }
            }else {
                animator.start();
            }
            return true;
        }
        return false;
    }

    private void drawArc(Canvas canvas) {
        canvas.save();

        int length = Math.min(getWidth(),getHeight());

        RectF rectF = new RectF(length*0.1f, (float) (length*0.1),
                length*0.9f,length*0.9f);


        canvas.drawArc(rectF, mStartAngle, mSweepAngle,false,mArcPaint);

        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        canvas.save();
        int length = Math.min(getWidth(),getHeight());
        mCircleX = length/2;
        mCircleY = mCircleX;
        mRadius = length*0.5f/2;

        canvas.drawCircle(mCircleX,mCircleY,mRadius,mCirclePaint);
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.save();
        // Draw the text.
        canvas.drawText(mText,
                getWidth() / 2,
                getWidth() / 2,
//                0,0,
                mTextPaint);
    }


    public String getText() {
        return mText;
    }


    public void setText(String exampleString) {
        mText = exampleString;
        invalidateTextPaintAndMeasurements();
    }


    public float getArcWidth() {
        return mArcWidth;
    }


    public void setTextSize(float textSize) {
        mTextSize = textSize;
        invalidateTextPaintAndMeasurements();
    }

}

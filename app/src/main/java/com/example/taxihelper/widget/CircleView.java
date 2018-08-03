package com.example.taxihelper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.taxihelper.R;

/**
 * Created by Raven on 2017/8/9.
 */

public class CircleView extends View {
    
    private int color;
    private Paint mPaint;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(color);
        invalidate();//重绘
        
    }

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //拿到属性集合
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView,defStyleAttr,0);
        color = a.getColor(R.styleable.CircleView_color, Color.GRAY);//默认灰色
        a.recycle();
        init();
    }
    
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width/2,height/2);
        canvas.drawCircle(width/2,height/2,radius,mPaint);
    }
}

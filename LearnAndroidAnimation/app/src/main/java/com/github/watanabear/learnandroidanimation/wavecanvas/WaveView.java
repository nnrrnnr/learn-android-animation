package com.github.watanabear.learnandroidanimation.wavecanvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.github.watanabear.learnandroidanimation.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by watanabear on 2017/04/08.
 */

public class WaveView extends View {



    Paint mPaint;
    Path mPath;

    double unit = 200;
    double xAxis;
    double yAxis;

    int width;
    int height;

    double t;

    Timer timer;
    Handler handler = new Handler();

    public WaveView(Context context) {
        super(context, null);
    }

    public WaveView(Context context, AttributeSet attr) {
        super(context, attr);
        setup(context, attr);
    }

    private void setup(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView,
                0, 0);
        int color = a.getInteger(R.styleable.WaveView_wave_color, Color.BLACK);

        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        t += 0.01;
                        invalidate();
                    }
                });
            }
        }, 0, 10);

        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        xAxis = height / 2;

        mPath.moveTo(0, height);
        drawSine(t);
        mPath.lineTo(width + 10, height);
        mPath.close();

        canvas.clipRect(0, 0, width, height, Region.Op.INTERSECT);
        canvas.drawPath(mPath, mPaint);

    }

    private void drawSine(double t) {
        for (int i = (int) yAxis; i <= width + 10; i += 5) {
            double x = t + (-yAxis + i) / unit;
            double y = Math.sin(x) / 4;
            mPath.lineTo((float) i, (float) (unit * y + xAxis));
        }
    }
}

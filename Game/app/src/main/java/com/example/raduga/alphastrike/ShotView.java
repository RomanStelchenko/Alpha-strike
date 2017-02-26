package com.example.raduga.alphastrike;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by gardi on 26.02.2017.
 */
public class ShotView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{

    private float centerX, centerY;
    private float radius;
    public ShotViewListener shotViewCallback;

    public ShotView(Context context) {
        super(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        shotViewCallback = (ShotViewListener) context;
    }

    public ShotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        shotViewCallback = (ShotViewListener) context;
    }

    public ShotView(Context context, AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        shotViewCallback = (ShotViewListener) context;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setupDimensions();
        drawView();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void setupDimensions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = Math.min(getWidth(), getHeight()) / 3;
    }

    public void drawView() {
        if(this.getHolder().getSurface().isValid()) {
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors  = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerY, radius, colors);
            colors.reset();
            colors.setARGB(255, 212, 36 ,54);//red
            canvas.drawLine(centerX, centerY + radius/8, centerX, centerY + (radius*7/8), colors);
            canvas.drawLine(centerX + radius/8, centerY, centerX + (radius*7/8), centerY, colors);
            canvas.drawLine(centerX, centerY - radius/8, centerX, centerY - (radius*7/8), colors);
            canvas.drawLine(centerX - radius/8, centerY, centerX - (radius*7/8), centerY, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public boolean onTouch(View v, MotionEvent e) {

        if(v.equals(this)) {
            if(e.getAction() == e.ACTION_DOWN) {
                shotViewCallback.onShotViewPressed();
            }
        }

        return true;
    }

    public interface ShotViewListener {
        void onShotViewPressed();
    }
}

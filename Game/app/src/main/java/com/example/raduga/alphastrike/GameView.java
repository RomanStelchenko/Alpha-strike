package com.example.raduga.alphastrike;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by gardi on 25.02.2017.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Tank tank;
    private Bitmap tankSprite;

    public GameView(Context context) {
        super(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);

        tankSprite = BitmapFactory.decodeResource(getResources(), R.drawable.tank_up_32_32);
        tank = new Tank(tankSprite, 32, 32);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);

        tankSprite = BitmapFactory.decodeResource(getResources(), R.drawable.tank_up_32_32);
        tank = new Tank(tankSprite, 32, 32);
    }

    public GameView(Context context, AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);

        tankSprite = BitmapFactory.decodeResource(getResources(), R.drawable.tank_up_32_32);
        tank = new Tank(tankSprite, 32, 32);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawTank(JoystickView.MOVE_NONE);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void drawTank(int direction) {
        if(this.getHolder().getSurface().isValid()) {
            Canvas canvas = this.getHolder().lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            tank.onDraw(canvas, direction);
            this.getHolder().unlockCanvasAndPost(canvas);
        }
    }
}
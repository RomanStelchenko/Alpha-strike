package com.example.raduga.alphastrike;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by gardi on 14.02.2017.
 */
public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float centerX, centerY;
    private float baseRadius, hatRadius;
    private float displacement, angle;

    public static final int MOVE_NONE = 0;
    public static final int MOVE_UP = 1;
    public static final int MOVE_DOWN = 2;
    public static final int MOVE_LEFT = 3;
    public static final int MOVE_RIGHT = 4;

    public JoystickListener joystickCallback;

    public JoystickView(Context context) {
        super(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        joystickCallback = (JoystickListener) context;
    }

    public JoystickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        joystickCallback = (JoystickListener) context;
    }

    public JoystickView(Context context, AttributeSet attributes, int style) {
        super(context, attributes, style);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setOnTouchListener(this);

        joystickCallback = (JoystickListener) context;
    }

    private void setupDimensions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 6;
    }

    private void drawJoystick(float newX, float newY) {
        if(this.getHolder().getSurface().isValid()) {
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.reset();
            colors.setARGB(255, 0, 0, 255);
            canvas.drawCircle(newX, newY, hatRadius, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private float calculateDisplacement(float x, float y) {
        return (float) Math.sqrt( (Math.pow(x - centerX, 2)) + Math.pow(y - centerY, 2) );
    }

    private double calculateAngle(float x, float y) {
        float posX = x - centerX;
        float posY = y - centerY;

        if(posX >= 0 && posY >= 0)
            return Math.toDegrees(Math.atan(posY/posX));
        else if(posX < 0 && posY >= 0)
            return Math.toDegrees(Math.atan(posY/posX)) + 180;
        else if(posX < 0 && posY < 0)
            return Math.toDegrees(Math.atan(posY/posX)) + 180;
        else if(posX >= 0 && posY < 0)
            return Math.toDegrees(Math.atan(posY/posX)) + 360;

        return 0;
    }

    public int getCurrentDirection() {
        if(angle >= 225  && angle < 315)
            return MOVE_UP;
        else if(angle >= 315 || angle < 45)
            return MOVE_RIGHT;
        else if(angle >= 45 && angle < 135)
            return MOVE_DOWN;
        else if(angle >= 135 && angle < 225)
            return MOVE_LEFT;

        return MOVE_NONE;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public boolean onTouch(View v, MotionEvent e) {
        if(v.equals(this)) {
            if(e.getAction() != e.ACTION_UP) {
                displacement = calculateDisplacement(e.getX(), e.getY());
                angle = (float) calculateAngle(e.getX(), e.getY());

                if(displacement < baseRadius) {
                    drawJoystick(e.getX(), e.getY());
                    joystickCallback.onJoystickMoved(getCurrentDirection());
                }
                else {
                    float ratio = baseRadius/displacement;
                    float constrainedX = centerX + (e.getX() - centerX) * ratio;
                    float constrainedY = centerY + (e.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickCallback.onJoystickMoved(getCurrentDirection());
                }
            }
            else {
                drawJoystick(centerX, centerY);
                joystickCallback.onJoystickMoved(MOVE_NONE);
            }
        }

        return true;
    }

    public interface JoystickListener {
        void onJoystickMoved(int derection);
    }
}
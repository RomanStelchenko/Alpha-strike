package com.example.raduga.alphastrike;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by gardi on 26.02.2017.
 */
public class Tank {

    private Bitmap tankSprite;
    private int currentPosX, currentPosY;
    private int width, height;
    private int speed;

    public Tank(Bitmap tankSprite, int startPosX, int startPosY) {
        this.tankSprite = tankSprite;
        this.speed = 5;

        this.currentPosX = startPosX;
        this.currentPosY = startPosY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void updatePosition(int direction) {
        switch(direction) {
            case JoystickView.MOVE_UP:
                currentPosY -= speed;
                break;
            case JoystickView.MOVE_DOWN:
                currentPosY += speed;
                break;
            case JoystickView.MOVE_LEFT:
                currentPosX -= speed;
                break;
            case JoystickView.MOVE_RIGHT:
                currentPosX += speed;
                break;
            default:
                break;
        }
    }

    public void onDraw(Canvas canvas, int direction) {
        updatePosition(direction);
        canvas.drawBitmap(tankSprite, currentPosX, currentPosY, null);
    }
}
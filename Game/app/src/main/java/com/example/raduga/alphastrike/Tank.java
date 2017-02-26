package com.example.raduga.alphastrike;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by gardi on 26.02.2017.
 */
public class Tank {

    private Bitmap tankSprite;
    private int currentPosX, currentPosY;
    private int width, height;
    private int currentFrame;
    private int speed;

    public Tank(Bitmap tankSprite, int startPosX, int startPosY) {
        this.tankSprite = tankSprite;
        this.speed = 5;

        this.currentPosX = startPosX;
        this.currentPosY = startPosY;

        this.currentFrame = 0;
        this.width = tankSprite.getWidth() / 2;
        this.height = tankSprite.getHeight() / 2;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void updatePosition(int direction) {
        switch(direction) {
            case JoystickView.MOVE_UP:
                currentPosY -= speed;
                currentFrame = 0;
                break;
            case JoystickView.MOVE_DOWN:
                currentPosY += speed;
                currentFrame = 1;
                break;
            case JoystickView.MOVE_LEFT:
                currentPosX -= speed;
                currentFrame = 2;
                break;
            case JoystickView.MOVE_RIGHT:
                currentPosX += speed;
                currentFrame = 3;
                break;
            default:
                break;
        }
    }

    public void onDraw(Canvas canvas, int direction) {
        updatePosition(direction);

        int srcX = width * (currentFrame % 2);
        int srcY = height * (currentFrame / 2);
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(currentPosX, currentPosY, currentPosX + width, currentPosY + height);

        canvas.drawBitmap(tankSprite, src, dst, null);
    }
}
package com.example.raduga.alphastrike;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements JoystickView.JoystickListener, ShotView.ShotViewListener {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        gameView = (GameView) findViewById(R.id.gameView);
    }

    @Override
    public void onJoystickMoved(int direction) {
        gameView.drawTank(direction);
    }

    @Override
    public void onShotViewPressed() {

    }
}
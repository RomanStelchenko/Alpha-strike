package com.example.raduga.alphastrike;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements JoystickView.JoystickListener{

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //JoystickView joystickView = new JoystickView(this);

        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onJoystickMoved(int direction) {
        switch(direction) {
            case JoystickView.MOVE_UP:
                textView.setText("UP");
                break;
            case JoystickView.MOVE_DOWN:
                textView.setText("DOWN");
                break;
            case JoystickView.MOVE_LEFT:
                textView.setText("LEFT");
                break;
            case JoystickView.MOVE_RIGHT:
                textView.setText("RIGHT");
                break;
            default:
                textView.setText("NONE");
                break;
        }
    }
}
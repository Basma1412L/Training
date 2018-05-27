package com.example.basma.training;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SensorAnimation extends AppCompatActivity implements SensorEventListener {

    ImageView kilu;
    //ObjectAnimator anim;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    public void onSensorChanged(SensorEvent event) {

        TextView txt=(TextView)findViewById(R.id.textView2);
        float xAxis = event.values[0];
        float yAxis = event.values[1];
        float zAxis = event.values[2];
        txt.setText(xAxis+","+yAxis+","+zAxis);
        kilu=(ImageView)findViewById(R.id.kil);
        ObjectAnimator anim = ObjectAnimator.ofFloat(kilu, "rotation", 0, 90);
        anim.setDuration(3000);                  // Duration in milliseconds
        anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.start();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void stopSensor(){
        mSensorManager.unregisterListener(this,mSensor);
    }

    private void startSensor(){
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    Display display;
    Point size;
    int width;
    int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_animation);
        display=getWindowManager().getDefaultDisplay();
        size=new Point();
        display.getSize(size);
        width=size.x;
        height=size.y;
         mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
         mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
         startSensor();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(SensorAnimation.this, SettingsActivity.class));
            return true;
        }

        else
        if (id == R.id.myPrefs) {
            // launch settings activity
            startActivity(new Intent(SensorAnimation.this, ChoicesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_sensor_animation);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            TextView txt=(TextView)findViewById(R.id.textView2);
            txt.setText("ho");
            kilu=(ImageView)findViewById(R.id.kil);
            ObjectAnimator anim = ObjectAnimator.ofFloat(kilu, "y", 100, -100);
            anim.setDuration(1000);                  // Duration in milliseconds
            anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.start();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            kilu=(ImageView)findViewById(R.id.kil);
             ObjectAnimator anim = ObjectAnimator.ofFloat(kilu, "x", 100, -100);
            anim.setDuration(1000);                  // Duration in milliseconds
            anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.start();
        }
    }
}

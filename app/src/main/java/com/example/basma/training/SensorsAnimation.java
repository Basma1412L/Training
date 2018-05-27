package com.example.basma.training;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SensorsAnimation extends AppCompatActivity {
    ImageView img;
    private SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener listener;
    float pre;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_animation);
        img = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(final SensorEvent event) {
                if (sensor.getType() == Sensor.TYPE_LIGHT) {
                    if (flag==false) {
                        pre = event.values[0];
                        flag = true;
                    }
                    if (pre<event.values[0]) {
                        final ObjectAnimator obj = ObjectAnimator.ofFloat(img, "x", (event.values[0])%(600));
                        obj.setDuration(300);
                        obj.setRepeatMode(ValueAnimator.REVERSE);
                        obj.setRepeatCount(1);
                        obj.start();


                    }else{
                        ObjectAnimator obj = ObjectAnimator.ofFloat(img, "x", -event.values[0]);
                        obj.setDuration(300);
                        obj.setRepeatMode(ValueAnimator.REVERSE);
                        obj.setRepeatCount(1);
                        obj.start();
                    }


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_GAME);
    }
}
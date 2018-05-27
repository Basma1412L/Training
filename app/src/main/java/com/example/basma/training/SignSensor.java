package com.example.basma.training;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class SignSensor extends AppCompatActivity implements SensorEventListener {

    private static final int THRESHOLD = 300;
    private static final String TAG ="MainActivity";
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int mCount, mStep;
    private long lastUpdate;
    private float last_x, last_y, last_z;
    private TextView txt_password;
    private Timer mTimer;
    private int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_sensor);

        txt_password = (TextView)findViewById(R.id.txt_password);
        time = 0;
        mCount = 0;
        mStep = 0;
        lastUpdate = 0;
        last_x = 0;
        last_y = 0;
        last_z = 0;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        startTimer();


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            long currentTime = System.currentTimeMillis();
            float [] values =sensorEvent.values;
            float x,y,z;
            if(lastUpdate != 0 ){
                long diff = currentTime - lastUpdate;
                if(diff > 500){
                    lastUpdate = currentTime;
                    x = values[0];
                    y = values[1];
                    z = values[2];

                    float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diff * 10000;
                    if (speed > THRESHOLD) {
                        updateCount();
                        Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }else{}
            }else{
                lastUpdate = currentTime;
                x = values[0];
                y = values[1];
                z = values[2];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) /  10000;
                if (speed > THRESHOLD) {
                    Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    updateCount();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void updateCount() {
        mCount++;
        switch (mStep){
            case 0:
                if(mCount > 0) {
                    mStep =1;
                    mCount = 0;
                    txt_password.setText(1+"");
                }else{

                }
                break;
            case 1:
                if (mCount>1){
                    mCount =0;
                    mStep =2;
                    txt_password.setText("1"+"- 2");
                }else{}
                break;
            case 2:
                if (mCount>3){
                    mSensorManager.unregisterListener(this,mSensor);
                    Toast.makeText(getApplicationContext(),"Correct Password",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(),Animations.class));
                    mTimer.cancel();
                    finish();
                }
                break;

        }
    }
    private void stopSensor(){
        mSensorManager.unregisterListener(this,mSensor);
    }

    private void startSensor(){
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_GAME);
    }
    private void startTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override

            public void run() {
                time++;
                if (time > 10) {
                    stopSensor();
                    txt_password.post(new Runnable() {
                        @Override
                        public void run() {
                            txt_password.setText("Please Try again after 5 seconds");
                        }
                    });
                    if (time > 15) {
                        txt_password.post(new Runnable() {
                            @Override
                            public void run() {
                                txt_password.setText("Enter Password");
                            }
                        });
                        startSensor();
                        time = 0;
                    }

                } else {

                }

            }
        }, 0, 1000);
    }
}

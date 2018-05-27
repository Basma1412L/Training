package com.example.basma.training;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class FirebaseSensorAnimation extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor prox_sensor;
    private Sensor pressure_sensor;
    TextView val;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String[] randomVals={"Lelouch","Nine","Canada","Deep Learning","Dark Chocolate"};

    Display display;
    Point size;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_sensor_animation);
        val=(TextView)findViewById(R.id.val);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        prox_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pressure_sensor=mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        database = Utils.getDatabase();
        myRef = database.getReference("value");
        startSensor();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {

            Random rand = new Random();
            int value = rand.nextInt(5);
            String written = randomVals[value];
            myRef.setValue(written);
            val=(TextView) findViewById(R.id.val);
            display=getWindowManager().getDefaultDisplay();
            size=new Point();
            display.getSize(size);
            width=size.x;
            height=size.y;
            ObjectAnimator anim = ObjectAnimator.ofFloat(val, "y", height/2, -height/2);
            anim.setDuration(1000);                  // Duration in milliseconds
            anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.start();



        }

        else if(event.sensor.getType() == Sensor.TYPE_PRESSURE)
        {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    val=(TextView) findViewById(R.id.val);
                    val.setText(value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });

        }

        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    private void stopSensor()
    {
        mSensorManager.unregisterListener(this,prox_sensor);
        mSensorManager.unregisterListener(this,pressure_sensor);
    }

    private void startSensor(){
        mSensorManager.registerListener(this,prox_sensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,pressure_sensor, SensorManager.SENSOR_DELAY_GAME);
    }

}

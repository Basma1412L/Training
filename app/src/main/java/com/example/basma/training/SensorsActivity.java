package com.example.basma.training;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    TextView sens;
    TextView sens2;
    private float oldX, oldY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        sens=(TextView)findViewById(R.id.sensorRes);
        sens2=(TextView)findViewById(R.id.sens2);



        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SensorEventListener listener = new SensorEventListener() {
            public void onSensorChanged(SensorEvent event) {

                float xAxis = event.values[0];
                float yAxis = event.values[1];
                float zAxis = event.values[2];
                sens.setText(xAxis+","+yAxis+","+zAxis);

            }
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);

        Sensor sensor2 =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener listener2 = new SensorEventListener() {
            public void onSensorChanged(SensorEvent event) {

                float distance = event.values[0];
                sens2.setText(distance+"");
                if (distance < 8) {
                  //  Toast.makeText(SensorsActivity.this,"Close ",Toast.LENGTH_SHORT).show();
                                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(listener2, sensor2,SensorManager.SENSOR_DELAY_NORMAL);



        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);


    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();


        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(SensorsActivity.this,"down at (\"+x+\",\"+y+\")\" ",Toast.LENGTH_SHORT).show();
            Log.i("TouchTest", "down at ("+x+","+y+")");
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            Toast.makeText(SensorsActivity.this,"up at ("+x+","+y+")",Toast.LENGTH_SHORT).show();

            Log.i("TouchTest", "up at ("+x+","+y+")");
        }
        return true;
    }
}

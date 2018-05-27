package com.example.basma.training;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity implements FragmentA.OnFragmentInteractionListener,FragmentB.OnFragmentInteractionListener{
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /** Called when the activity is first created. */


    Fragment fragment ;
    static Boolean inv=true;
    private MyReceiver broadcastReceiver;
    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        fragmentManager = getSupportFragmentManager();
        broadcastReceiver= new MyReceiver();
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.TIME_TICK");
        registerReceiver(broadcastReceiver,intentFilter);

    }
    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    public void startAlert(View view) {
        EditText text = (EditText) findViewById(R.id.time);
        int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " seconds",
                Toast.LENGTH_LONG).show();
    }


    public static class MyReceiver extends BroadcastReceiver {

        public MyReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentB fragmentB = new FragmentB();
            FragmentA fragmentA = new FragmentA();

            if(!inv)
            { fragmentTransaction.add(R.id.frame, fragmentA);
                fragmentTransaction.commit();}
            else
            {
                fragmentTransaction.add(R.id.frame, fragmentB);
                fragmentTransaction.commit();
            }
            inv=!inv;

        }
    }

}
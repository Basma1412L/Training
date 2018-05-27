package com.example.basma.training;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

public class MyService2 extends Service {
    public MyService2() {
    }

    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();




    }
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {


        String userID = intent.getStringExtra("midllat");

        player = MediaPlayer.create(this, R.raw.tedx);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        Intent intnt = new Intent("YourAction");
        Bundle bundle = new Bundle();
        bundle.putString("valueName", userID);
        bundle.putDouble("doubleName", 25);
        intnt.putExtras(bundle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intnt);

        return 1;
    }

    public void onStart(Intent intent, int startId) {

    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}

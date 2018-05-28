package com.example.basma.training;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class Speech1Activity extends DrawerActivity {

    private TextToSpeech mtts;
    EditText textEntered;
    TextView pitchTv;
    SeekBar pitchSeekBar;
    TextView speedTv;
    SeekBar speedSeekBar;
    Button speak;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech1);

        textEntered = (EditText)findViewById(R.id.enterText);
        pitchTv = (TextView)findViewById(R.id.pitchTv);
        pitchSeekBar = (SeekBar)findViewById(R.id.pitchSeekBar);
        speedTv = (TextView)findViewById(R.id.speedTv);
        speedSeekBar = (SeekBar)findViewById(R.id.speedSeekBar);
        speak = (Button)findViewById(R.id.speckBtn);
        go = (Button)findViewById(R.id.go_btn);

        //initialize text to speech engine
        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //this is to check the status of the engine
                if (status == TextToSpeech.SUCCESS){
                    int result = mtts.setLanguage(Locale.getDefault()); //choose the language
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not Supported");
                    }

                    else {
                        speak.setEnabled(true);
                        //The button will not be enabled unless everything is verified and checked to be working properly
                    }
                }
                else {
                    Log.e("TTS","Initialization Failed");
                }
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakMethod();
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Speech1Activity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

    public void speakMethod(){
        String text = textEntered.getText().toString();
        float pitch =(float) pitchSeekBar.getProgress() / 50; //this to make the 50 progress initialized seekbar represents 1.0
        if (pitch < 0.1){
            pitch = 0.1f;
        }
        float speed =(float) speedSeekBar.getProgress() / 50;
        if(speed < 0.1){
            speed = 0.1f;
        }

        //apply these settings on the tts engine
        mtts.setPitch(pitch); //thickness of sound
        mtts.setSpeechRate(speed);
        mtts.speak(text, TextToSpeech.QUEUE_FLUSH,null);
    }

    //close the engine if the app is not working

    @Override
    protected void onDestroy() {
        if (mtts != null){
            mtts.stop();
            mtts.shutdown();
        }
        super.onDestroy();
    }
}

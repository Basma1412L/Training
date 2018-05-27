package com.example.basma.training;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {
    ImageButton mic;
    TextView tv;
    final int VOICE_RECOGNITION =100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mic = (ImageButton)findViewById(R.id.mic_btn);
        tv = (TextView)findViewById(R.id.textView);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput();
            }
        });
    }

    public void getSpeechInput(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Go Ahead");
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);

        try {
            startActivityForResult(i,VOICE_RECOGNITION);

        }
        catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Sorry your Device is not supported", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                tv.setText(results.get(0));
            }
        }

    }
}

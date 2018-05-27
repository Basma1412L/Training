package com.example.basma.training;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            String msg = "Hello, world!";
            tts.speak(msg, TextToSpeech.QUEUE_ADD, null);
        }
    }

    TextView write;
    TextView read;
    Button readBtn;
    Button writeBtn;
    TextToSpeech tts;
    final int CHECK_TTS_DATA =39;



    final int VOICE_RECOGNITION = 40;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        write=(TextView)findViewById(R.id.write);
        read = (TextView)findViewById(R.id.read);

        readBtn = (Button)findViewById(R.id.talkBtn);
        writeBtn=(Button)findViewById(R.id.speakBtn);


 readBtn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         String txt = write.getText().toString();
         tts.speak(txt, TextToSpeech.QUEUE_ADD, null);
     }
 });

 writeBtn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {



         Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
         intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak!");
         intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
         startActivityForResult(intent, VOICE_RECOGNITION);


     }
 });

 tts = new TextToSpeech(this, this);


        Intent intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, CHECK_TTS_DATA);
    }


    protected void onActivityResult(int requestCode, int responseCode, Intent data) {

        if (requestCode == CHECK_TTS_DATA) {
            if (responseCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                List<String> available = data.getStringArrayListExtra(TextToSpeech.Engine.EXTRA_AVAILABLE_VOICES);
                List<String> unavailable = data.getStringArrayListExtra(TextToSpeech.Engine.EXTRA_UNAVAILABLE_VOICES);
            }

        }

        if (requestCode == VOICE_RECOGNITION) {
            if (responseCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                float[] confidence =
                        data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
                String res="";
                for (String result : results) {
                    res+=result+" ";
                }
                write.setText(res);
            }
        }

        else {
            String action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA;
            Intent install = new Intent();
            install.setAction(action);
            startActivity(install);
        }
    }
}

package com.example.basma.training;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.SearchView;

public class ViewsActivity extends AppCompatActivity {


    SeekBar sb;
    ProgressBar pb;
    Button btn;
    Button btn2;
    ToggleButton tb;
    RatingBar rb;
    ObjectAnimator anim;


    Display display;
    Point size;
    int width;
    int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        sb=(SeekBar)findViewById(R.id.sb);
        pb=(ProgressBar)findViewById(R.id.pb);
        btn=(Button)findViewById(R.id.btn);
        btn2=(Button)findViewById(R.id.anm);
        tb=(ToggleButton)findViewById(R.id.tb);
        rb=(RatingBar)findViewById(R.id.rb);


        display=getWindowManager().getDefaultDisplay();
        size=new Point();
        display.getSize(size);
        width=size.x;
        height=size.y;

        SearchView simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery(); // get the query string currently in the text field

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                anim = ObjectAnimator.ofFloat(sb, "y", height, -height);
                anim.setDuration(1000);                  // Duration in milliseconds
                anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
                anim.setRepeatMode(ValueAnimator.REVERSE);
                anim.setRepeatCount(ValueAnimator.INFINITE);
                anim.start();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int seekBarValue= sb.getProgress();
                pb.setProgress(seekBarValue);

                int max = sb.getMax();
                if (seekBarValue>(max/2))
                {
                    rb.setRating(5);
                    tb.setTextOff("TOGGLE ON");
                    tb.setChecked(true);
                }
                else
                {
                    tb.setTextOn("TOGGLE OFF");
                    tb.setChecked(false);
                }


            }
        });
    }
}

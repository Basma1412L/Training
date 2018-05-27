package com.example.basma.training;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {


    ObjectAnimator anim;
    ImageView kilua;
    ImageView kilua2;
    Button button;
    Button button2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kilua = (ImageView)findViewById(R.id.imageView2);
        kilua2 = (ImageView)findViewById(R.id.imageView3);

        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);

     //   final ObjectAnimator animator = ObjectAnimator.ofFloat(kilua,"x",550,-1000);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(kilua2,"y",550,-1000);



      final  ImageView imageview = (ImageView) findViewById(R.id.imageView2);
        // Create the AnimationDrawable in which we will store all frames of the animation
        final AnimationDrawable animationDrawable = new AnimationDrawable();
        final AnimationDrawable animationDrawable2 = new AnimationDrawable();
        BitmapDrawable bitmap= (BitmapDrawable)getResources().getDrawable(R.drawable.kilua);
        BitmapDrawable bitmap2= (BitmapDrawable)getResources().getDrawable(R.drawable.lilo);
        animationDrawable2.addFrame(bitmap,300);
        animationDrawable2.addFrame(bitmap2,300);

        kilua2.setImageDrawable(animationDrawable2);
        for (int i = 0; i < 10; ++i) {
            animationDrawable.addFrame(getDrawableForFrameNumber(i), 300);
        }
        // Run until we say stop
        animationDrawable.setOneShot(false);
        imageview.setImageDrawable(animationDrawable);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            animator2.setDuration(3000);
            animator2.setRepeatMode(ValueAnimator.REVERSE);
            animator2.setRepeatCount(ValueAnimator.INFINITE);
            animator2.setInterpolator(new LinearInterpolator());
            animator2.start();


                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                } else {
                    animationDrawable.start();
                }


                if (animationDrawable2.isRunning()) {
                    animationDrawable2.stop();
                } else {
                    animationDrawable2.start();
                }
                // Pass our animation drawable to our custom drawable class
                CustomAnimationDrawableNew cad = new CustomAnimationDrawableNew(animationDrawable) {
                    @Override
                    void onAnimationStart() {
                        // Animation has started...

                        Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    void onAnimationFinish() {
                        // Animation has finished...
                        Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();

                    }
                };

                // Set the views drawable to our custom drawable
                // Start the animation
                cad.start();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animator2.cancel();
            }
        });











   /*     ImageView iv = imageview;

        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                // Pass our animation drawable to our custom drawable class
                CustomAnimationDrawableNew cad = new CustomAnimationDrawableNew(animationDrawable) {
                    @Override
                    void onAnimationStart() {
                        // Animation has started...

                        Toast.makeText(MainActivity.this,"Started",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    void onAnimationFinish() {
                        // Animation has finished...
                        Toast.makeText(MainActivity.this,"Finished",Toast.LENGTH_SHORT).show();

                    }
                };

                // Set the views drawable to our custom drawable
                v.setBackgroundDrawable(cad);

                // Start the animation
                cad.start();
            }
        });*/















    }


    private BitmapDrawable getDrawableForFrameNumber(int frameNumber) {
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLUE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(80);
        paint.setColor(Color.BLACK);
        canvas.drawText("Frame " + frameNumber, 40, 220, paint);
        return new BitmapDrawable(getResources(), bitmap);
    }
}

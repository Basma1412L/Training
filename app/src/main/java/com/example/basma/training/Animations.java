package com.example.basma.training;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Animations extends AppCompatActivity {

    ImageView img;
    EditText seconds;
    Button mov1;
    Button mov2;
    Button mov3;
    ObjectAnimator anim;
    ObjectAnimator anim1;
     ObjectAnimator anim2;
     ObjectAnimator anim3;
    AnimatorSet animation;
    Display display;
    Point size;
    int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        img=(ImageView)findViewById(R.id.imageView6);
        seconds=(EditText)findViewById(R.id.editText4);
        mov1=(Button)findViewById(R.id.move1);
        mov2=(Button)findViewById(R.id.move2);
        mov3=(Button)findViewById(R.id.move3);


        anim1= ObjectAnimator.ofFloat(img, "rotation", 0, 360);
        anim2=ObjectAnimator.ofFloat(img, "scaleX", 1, 2f);
        anim3=ObjectAnimator.ofFloat(img, "scaleY", 1, 2f);
        animation = new AnimatorSet();
        animation.playTogether(anim1,anim2,anim3 );


        display=getWindowManager().getDefaultDisplay();
        size=new Point();
        display.getSize(size);
        width=size.x;
        height=size.y;



        mov1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int secnds=Integer.parseInt(seconds.getText().toString());

                int imageWidth = img.getMeasuredWidth();
                float source = img.getX();
                float dest = -imageWidth;


                anim = ObjectAnimator.ofFloat(img, "x", source, dest);
                anim.setDuration(secnds*1000);                  // Duration in milliseconds
                anim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
                anim.setRepeatMode(ValueAnimator.REVERSE);
                anim.setRepeatCount(ValueAnimator.INFINITE);
                anim.start();

            }
        });


        mov2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int secnds=Integer.parseInt(seconds.getText().toString());
                animation.setDuration(secnds*1000);                  // Duration in milliseconds
                animation.setInterpolator(new AccelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
                anim1.setRepeatMode(ValueAnimator.REVERSE);
                anim1.setRepeatCount(ValueAnimator.INFINITE);
                anim2.setRepeatMode(ValueAnimator.REVERSE);
                anim2.setRepeatCount(ValueAnimator.INFINITE);
                anim3.setRepeatMode(ValueAnimator.REVERSE);
                anim3.setRepeatCount(ValueAnimator.INFINITE);
                animation.start();

            }
        });

        mov3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Animations.this, SensorsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(Animations.this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);
                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Animations.this)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle("FCM Message")
                        .setContentText("It is a notification")  //this is the message body received
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

/*
                if(anim.isRunning())
                {
                    anim.end();
                }
                if(animation.isRunning())
                {
                    animation.end();
                }
*/
            }
        });




        final AnimationDrawable animationDrawable2 = new AnimationDrawable();
        BitmapDrawable bitmap= (BitmapDrawable)getResources().getDrawable(R.drawable.kilua);
        BitmapDrawable bitmap2= (BitmapDrawable)getResources().getDrawable(R.drawable.lilo);
        animationDrawable2.addFrame(bitmap,300);
        animationDrawable2.addFrame(bitmap2,300);
        animationDrawable2.setOneShot(false);
        img.setImageDrawable(animationDrawable2);
        animationDrawable2.start();



        ImageView img = (ImageView)findViewById(R.id.spinning_wheel_image);
        img.setBackgroundResource(R.drawable.spin_animation);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

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

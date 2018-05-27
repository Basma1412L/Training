package com.example.basma.training;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChoicesActivity extends AppCompatActivity {

    EditText name;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices);
        name=(EditText)findViewById(R.id.editText5);
        done = (Button)findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();

                SharedPreferences settingsfile= getSharedPreferences("Choices",0);
                SharedPreferences.Editor myeditor = settingsfile.edit();
                    myeditor.putString("name", n);
                    myeditor.apply();


                SharedPreferences mysettings = getSharedPreferences("Choices", 0);
                String nn = mysettings.getString("name", "nn");

                Toast.makeText(ChoicesActivity.this, "Hi"+nn, Toast.LENGTH_SHORT).show();



            }
        });

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /* When focus is lost check that the text field
                 * has valid values.
                 */
                if (!hasFocus) {
               //     Toast.makeText(ChoicesActivity.this, "You are not  typing", Toast.LENGTH_SHORT).show();

                }
            }
        });

        name.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                name.getWindowVisibleDisplayFrame(r);

                int heightDiff = name.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
          //          Toast.makeText(ChoicesActivity.this,"You are typing",Toast.LENGTH_SHORT).show();
                } else {
//to make sure this will call only once when keyboard is hide.

               //     Toast.makeText(ChoicesActivity.this, "You are not  typing", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}

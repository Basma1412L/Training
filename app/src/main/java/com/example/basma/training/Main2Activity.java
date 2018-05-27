package com.example.basma.training;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import java.io.ByteArrayOutputStream;

public class Main2Activity extends AppCompatActivity {

    ImageView ppdialog;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    static final int PICK_CONTACT_REQUEST = 1; // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    public void onRadioButtonClicked(View v)
    {

        switch(v.getId())
        {
            case R.id.one:
            {
                Toast.makeText(this, "I am the first choice", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.two:
            {
                Toast.makeText(this, "I am the second choice", Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.three:
            {
                Toast.makeText(this, "I am the third choice", Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.button3:
            {
                RadioGroup radiogroup= (RadioGroup)findViewById(R.id.group);
                int id = radiogroup.getCheckedRadioButtonId();
                if (!(id==-1))
                {
                RadioButton radioBtn = (RadioButton)findViewById(id);
                String text = radioBtn.getText().toString();
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();}

            }
            break;
            case R.id.imageView:
            {
                capturePhoto();
            }
            break;
            case R.id.button4:
            {
                pickContact();
            }
        }
    }


    public void capturePhoto() {


            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }



    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {

                Uri contactUri = data.getData();
                String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = this.getContentResolver().query(contactUri, projection, null, null, null);

                // If the cursor returned is valid, get the phone number
                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberIndex);
                    TextView num = (TextView) findViewById(R.id.number);
                    num.setText(number);
                }
            }
        }

        else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}


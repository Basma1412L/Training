package com.example.basma.training;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class RestFirebase extends AppCompatActivity {


    Button upload;
    Button download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_firebase);
        upload=(Button)findViewById(R.id.upload);
        download=(Button)findViewById(R.id.download);
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String filePath = Environment.getExternalStorageDirectory().toString() + "/Download";
                String fileName = "images.jpg";

                File f = new File(filePath,fileName);

                Uri file = Uri.fromFile(f);

                StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });

            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/rivers.jpg");

            @Override
            public void onClick(View v) {

                File localFile = null;
                try {
                    localFile = File.createTempFile("images", "jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                storageRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(RestFirebase.this,"Done", Toast.LENGTH_SHORT).show();
                                // Successfully downloaded data to local file
                                // ...
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });

            }
        });
    }
}

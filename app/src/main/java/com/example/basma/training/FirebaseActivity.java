package com.example.basma.training;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirebaseActivity extends AppCompatActivity {

    FirebaseDatabase database ;
    EditText senderName;
    Button ok;
    String sender;
    Button sendMsg;
    EditText cont;
    ListView messagesList;
    List<Message> fullChat=new ArrayList<>();
    MessageAdapter adapter;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        senderName=(EditText) findViewById(R.id.senderName);
        sendMsg=(Button)findViewById(R.id.send);
        cont=(EditText)findViewById(R.id.editText3);
        ok=(Button)findViewById(R.id.doneName);
        messagesList=(ListView)findViewById(R.id.list);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        adapter = new MessageAdapter(FirebaseActivity.this,fullChat);
        messagesList.setAdapter(adapter);


        database = Utils.getDatabase();
        final DatabaseReference myRef = database.getReference("Message");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sender = senderName.getText().toString();
            }
        });



        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = cont.getText().toString();
                String currDate =""+ Calendar.getInstance().getTime();
                Message newMsg = new Message(sender,content,currDate);
                String id = myRef.push().getKey();
                myRef.child(id).setValue(newMsg);
                fullChat.add(newMsg);
                adapter.notifyDataSetChanged();

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final DataSnapshot dataSnapshot1=dataSnapshot;
                fullChat.clear();
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                        Message currMsg=data.getValue(Message.class);
                        fullChat.add(currMsg);

                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}

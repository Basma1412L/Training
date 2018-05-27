package com.example.basma.training;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {



    DatabaseHelper dbHelper;
    Button save;
    ArrayList<item> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        final   EditText  name=(EditText)findViewById(R.id.editText);
        final EditText country = (EditText)findViewById(R.id.editText2);

        dbHelper = new DatabaseHelper(this);
        save=(Button)findViewById(R.id.button11);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameStr = name.getText().toString();
                String countryTxt = country.getText().toString();


                boolean is_inserted = dbHelper.insertData(nameStr,countryTxt);
                if (is_inserted) {
                    Toast.makeText(DatabaseActivity.this, "Task Inserted", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(DatabaseActivity.this, "Task  Not Inserted", Toast.LENGTH_SHORT).show();

                }

                viewAll();




            }
        });



    }
    public void viewAll() {
        items.clear();
        Cursor res = dbHelper.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Task :" + res.getString(1) + "\n");
            item i  = new item(res.getString(0),res.getString(1),res.getString(2));
            items.add(i);
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message) {

        final CustomAdapter myAdapter = new CustomAdapter(this,items) ;
        ListView myList = (ListView)findViewById(R.id.list);
        myList.setAdapter(null);
        myList.setAdapter(myAdapter);

    }

}



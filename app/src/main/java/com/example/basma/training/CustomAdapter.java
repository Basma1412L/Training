package com.example.basma.training;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {


    private Activity context;
    private List<item> booksList;

    @NonNull
    @Override
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public List<item> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<item> booksList) {
        this.booksList = booksList;
    }

    public CustomAdapter(@NonNull Context context, int resource, Activity context1, List<item> booksList) {
        super(context, resource);
        this.context = context1;
        this.booksList = booksList;
    }

    public CustomAdapter(@NonNull Context context, int resource, int textViewResourceId, Activity context1, List<item> booksList) {
        super(context, resource, textViewResourceId);
        this.context = context1;
        this.booksList = booksList;
    }



    public CustomAdapter(Activity context1, List<item> booksList) {
        super(context1, R.layout.item,booksList);
        this.context = context1;
        this.booksList = booksList;
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  listViewItem = inflater.inflate(R.layout.item,null,true);

        final TextView txtID=(TextView)listViewItem.findViewById(R.id.id);
        final TextView txtName=(TextView)listViewItem.findViewById(R.id.name);
        final  TextView txtCountry=(TextView)listViewItem.findViewById(R.id.country);

        final item book =booksList.get(position);

        txtID.setText((book.getId()));
        txtName.setText(book.getName());
        txtCountry.setText(book.getCountry());

        return listViewItem;
    }









}


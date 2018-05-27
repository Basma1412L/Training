package com.example.basma.training;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter {


    private Activity context;
    private List<Message> msgList;

    @NonNull
    @Override
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public List<Message> getBooksList() {
        return msgList;
    }

    public void setBooksList(List<Message> msgList) {
        this.msgList = msgList;
    }




    public MessageAdapter(Activity context1, List<Message> msgList) {
        super(context1, R.layout.item,msgList);
        this.context = context1;
        this.msgList = msgList;
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  listViewItem = inflater.inflate(R.layout.chat_item,null,true);

        final TextView txtName=(TextView)listViewItem.findViewById(R.id.sender);
        final  TextView txtContext=(TextView)listViewItem.findViewById(R.id.context);
        final TextView txtDate=(TextView)listViewItem.findViewById(R.id.date);

        final Message msg =msgList.get(position);

        txtName.setText((msg.getSender()));
        txtContext.setText(msg.getContent_sent());
        txtDate.setText(msg.getDate_sent());

        return listViewItem;
    }









}


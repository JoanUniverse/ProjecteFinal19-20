package com.example.myperfectteam.arrayadapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myperfectteam.R;
import com.example.myperfectteam.mptobjects.Message;

import java.util.ArrayList;

public class ArrayMessage extends ArrayAdapter<Message> {
    private Context context;
    private ArrayList<Message> messages;

    public ArrayMessage(@NonNull Context context, int resource, ArrayList<Message> messages) {
        super(context, resource, messages);
        this.context = context;
        this.messages = messages;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Message message = messages.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_list_item, null);

        TextView messageTextTV = view.findViewById(R.id.messageText);
        TextView messagePlayerNameTV = view.findViewById(R.id.messagePlayerName);
        TextView messageDateTV = view.findViewById(R.id.messageDate);

        String messageText = message.getMessageText();
        String messagePlayerName = message.getPlayerName();
        String messageDate = message.getMessageDate();

        messageTextTV.setText(messageText);
        messagePlayerNameTV.setText(messagePlayerName);
        messageDateTV.setText(messageDate);
        return view;
    }
}

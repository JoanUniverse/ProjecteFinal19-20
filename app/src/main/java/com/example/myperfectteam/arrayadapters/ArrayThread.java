package com.example.myperfectteam.arrayadapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myperfectteam.mptutilities.Preferences;
import com.example.myperfectteam.R;
import com.example.myperfectteam.mptobjects.ThreadObject;

import java.util.ArrayList;

public class ArrayThread extends ArrayAdapter<ThreadObject> {
    Preferences preferences;
    private Context context;
    private ArrayList<ThreadObject> threadObjects;

    public ArrayThread(@NonNull Context context, int resource, ArrayList<ThreadObject> threadObjects) {
        super(context, resource, threadObjects);
        this.context = context;
        this.threadObjects = threadObjects;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        preferences = new Preferences(getContext());
        final ThreadObject threadObject = threadObjects.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.thread_list_item, null);

        TextView playerTV = view.findViewById(R.id.threadPlayerName);
        TextView threadTitleTV = view.findViewById(R.id.threadTitle);
        TextView threadDescriptionTV = view.findViewById(R.id.threadDescription);
        TextView threadDateTV = view.findViewById(R.id.threadDate);

        playerTV.setText(threadObject.getPlayerName());
        threadTitleTV.setText(threadObject.getThreadTitle());
        threadDescriptionTV.setText(threadObject.getThreadDescription());
        threadDateTV.setText(threadObject.getThreadDate());
        return view;
    }
}

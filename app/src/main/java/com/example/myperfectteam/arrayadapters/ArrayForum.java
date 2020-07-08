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
import com.example.myperfectteam.mptobjects.Forum;

import java.util.ArrayList;

public class ArrayForum extends ArrayAdapter<Forum> {
    private Context context;
    private ArrayList<Forum> forums;

    public ArrayForum(@NonNull Context context, int resource, ArrayList<Forum> forums) {
        super(context, resource, forums);
        this.context = context;
        this.forums = forums;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Forum forum = forums.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.forum_list_item, null);

        TextView forumNameTV = view.findViewById(R.id.forumNameTV);
        TextView forumDescriptionTV = view.findViewById(R.id.forumDescriptionTV);
        String forumName = forum.getForumName();
        String forumDescription = forum.getForumDescription();

        forumNameTV.setText(forumName);
        forumDescriptionTV.setText(forumDescription);
        return view;
    }
}
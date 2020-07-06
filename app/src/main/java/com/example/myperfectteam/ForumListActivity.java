package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ForumListActivity extends AppCompatActivity {
    private ArrayList<Forum> forums = new ArrayList<>();
    private ListView listView;
    private ForumListActivity.ForumListener listener;
    private ArrayAdapter<Forum> adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);

        listView = findViewById(R.id.forumListView);
        forums.add(new Forum(0,"NEW PLAYERS", "This is a forum created for new players to learn faster about the game."));
        forums.add(new Forum(1,"LOOKING FOR TEAM", "Let's create a team together! Create your perfect team to achieve your goals"));

        adapter = new ArrayForum(getApplicationContext(), R.layout.forum_list_item, forums);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Forum forum = adapter.getItem(pos);
                String forumName = forum.getForumName();
                listener.onForumSelected(forumName);
            }
        });
    }
    public interface ForumListener {
        void onForumSelected(String forum);
    }
    public void setForumListener(ForumListActivity.ForumListener listener) {
        this.listener= listener;
    }
}

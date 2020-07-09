package com.example.myperfectteam.mptactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.R;
import com.example.myperfectteam.mptfragments.ThreadListFragment;
import com.example.myperfectteam.mptfragments.ThreadMessagesFragment;
import com.example.myperfectteam.mptobjects.ThreadObject;
import com.example.myperfectteam.mptutilities.Preferences;

public class ThreadListActivity extends AppCompatActivity implements ThreadListFragment.ForoListener, ThreadListFragment.FabListener{

    public static ThreadObject threadObjectSelected;
    ListView listView;
    TextView textViewBar;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Threads");
        Intent intent = getIntent();
        int forumID = intent.getIntExtra("forumID", 0);
        Toast.makeText(this, "FORUM ID: " + forumID, Toast.LENGTH_LONG).show();
        ThreadListFragment threadListFragment = (ThreadListFragment)
                getSupportFragmentManager().findFragmentById(R.id.FrgLlista);
        threadListFragment.setForoListener(this);
        threadListFragment.setFabListener(this);
    }

    @Override
    public void onFilSeleccionat(ThreadObject threadObject) {
        // Mirar l'orientació
        threadObjectSelected = threadObject;
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // estam en vertical
            Intent i = new Intent(this, ThreadMessagesActivity.class);
            startActivity(i);
        } else {
            // estam en horitzontal
            ((ThreadMessagesFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgFil)).mostraFil(threadObject);
        }
    }

    public void onFabSelected() {
        Intent i = new Intent(this, AddThreadActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                preferences.clearPreferences();
                Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

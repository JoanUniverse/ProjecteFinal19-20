package com.example.myperfectteam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myperfectteam.arrayadapters.ArrayMessage;
import com.example.myperfectteam.mptobjects.Message;
import com.example.myperfectteam.mptobjects.ThreadObject;
import com.example.myperfectteam.mptutilities.Preferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadMessagesFragment extends Fragment {
    private ArrayList<String> llista;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread_messages, container, false);
    }

    public void mostraFil(ThreadObject threadObject) {

    }
}


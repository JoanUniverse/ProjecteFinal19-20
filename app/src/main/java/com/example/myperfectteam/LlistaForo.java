package com.example.myperfectteam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LlistaForo extends Fragment {
    Preferences preferences;
    public static ArrayList<ThreadObject> threadObjects = new ArrayList<>();
    private ListView listView;
    private ForoListener listener;
    private FabListener fabListener;
    private ArrayAdapter<ThreadObject> adapter;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foro_llista, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        floatingActionButton = getView().findViewById(R.id.fab);
        preferences = new Preferences(getContext());
        new RequestAsync().execute();
        showThreads();
    }
    public interface ForoListener {
        void onFilSeleccionat(String fil);
    }
    public void setForoListener(ForoListener listener) {
        this.listener=listener;
    }

    public interface FabListener {
        void onFabSelected();
    }
    public void setFabListener(FabListener fabListener) {
        this.fabListener = fabListener;
    }

    public class RequestAsync extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                //GET Request
                HashMap<String, String> p = new HashMap<String, String>();
                return RequestHandler.sendGet(RequestHandler.GET_ALL_THREADS + "" + preferences.getLastForumID());
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Boolean response;
            try {
                JSONObject convertedObject = new JSONObject(s);
                response = convertedObject.getBoolean("correcta");
                if(response){
                    JSONArray jsonArray = convertedObject.getJSONArray("dades");
                    threadObjects = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        ThreadObject threadObject = new Gson().fromJson(explrObject.toString(), ThreadObject.class);
                        threadObjects.add(threadObject);
                    }
                    showThreads();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showThreads() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabListener.onFabSelected();
            }
        });
        listView = getView().findViewById(R.id.listView);
//        fils.add(new Fil(0,"Joan", "Es una prova"));
//        fils.add(new Fil(0,"Toni toni", "Es unaaaaa prova"));
//        fils.add(new Fil(0,"Bernat desclot 23", "Es una provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa a que si que es una prova ja ho se jo ja"));

        adapter = new ArrayFil(getContext(), R.layout.fil_list_item, threadObjects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
//                if (listener!=null) {
//                    listener.onFilSeleccionat((String) list.getAdapter().getItem(pos));
//                }
                ThreadObject threadObject = adapter.getItem(pos);
                String temaFil = threadObject.getThreadTitle();
                listener.onFilSeleccionat(temaFil);
                preferences.setLastThreadID(threadObject.getThreadID());
            }
        });
    }
}


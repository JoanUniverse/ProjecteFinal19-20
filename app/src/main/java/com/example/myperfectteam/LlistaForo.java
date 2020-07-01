package com.example.myperfectteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class LlistaForo extends Fragment {
    private ArrayList<Fil> fils = new ArrayList<>();
    private ListView listView;
    private ForoListener listener;
    private ArrayAdapter<Fil> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foro_llista, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = getView().findViewById(R.id.listView);
        fils.add(new Fil(0,"Joan", "Es una prova"));
        fils.add(new Fil(0,"Toni toni", "Es unaaaaa prova"));
        fils.add(new Fil(0,"Bernat desclot 23", "Es una provaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa a que si que es una prova ja ho se jo ja"));

        adapter = new ArrayFil(getContext(), R.layout.fil_list_item, fils);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
//                if (listener!=null) {
//                    listener.onFilSeleccionat((String) list.getAdapter().getItem(pos));
//                }
                Fil fil = adapter.getItem(pos);
                String temaFil = fil.getTema();
                listener.onFilSeleccionat(temaFil);
            }
        });
    }
    public interface ForoListener {
        void onFilSeleccionat(String fil);
    }
    public void setForoListener(ForoListener listener) {
        this.listener=listener;
    }
}


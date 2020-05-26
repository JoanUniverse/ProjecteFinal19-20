package com.example.projectefinal19_20;

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
    private ArrayList<String> llista;
    private ListView listView;
    private ForoListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foro_llista, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = getView().findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Primer tema");
        arrayList.add("Segon tema");
        arrayList.add("Tercer tema");
        arrayList.add("Primer tema");
        arrayList.add("Segon tema");
        arrayList.add("Tercer tema");
        arrayList.add("Primer tema");
        arrayList.add("Segon tema");
        arrayList.add("Tercer tema");
        arrayList.add("Primer tema");
        arrayList.add("Segon tema");
        arrayList.add("Tercer tema");

        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onFilSeleccionat((String) list.getAdapter().getItem(pos));
                }
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


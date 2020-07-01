package com.example.myperfectteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FilForo extends Fragment {
    private ArrayList<String> llista;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foro_fil, container, false);
    }

    public void mostraFil(String fil) {
        TextView tv = getView().findViewById(R.id.NomFil);
        tv.setText((fil));
    }
}

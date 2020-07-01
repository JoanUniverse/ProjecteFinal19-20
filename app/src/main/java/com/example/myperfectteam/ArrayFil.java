package com.example.myperfectteam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ArrayFil extends ArrayAdapter<Fil> {
    private Context context;
    private ArrayList<Fil> fils;

    public ArrayFil(@NonNull Context context, int resource, ArrayList<Fil> fils) {
        super(context, resource, fils);
        this.context = context;
        this.fils = fils;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        final Fil fil = fils.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fil_list_item, null);

        TextView usuariTV = view.findViewById(R.id.usuariFil);
        TextView missatgeTV = view.findViewById(R.id.missatgeFil);
        String usuari = fil.getUsuari();
        String missatge = fil.getTema();

        usuariTV.setText(usuari);
        missatgeTV.setText(missatge);
        return view;
    }
}

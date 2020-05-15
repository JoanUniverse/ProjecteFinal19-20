package com.example.projectefinal19_20;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
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

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);


    }
}

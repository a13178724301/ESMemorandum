package com.example.esmemorandum;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.esmemorandum.adapter.ArrayListItem;
import com.example.esmemorandum.adapter.ListViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        ArrayList<ArrayListItem> arrayListItems = new ArrayList<>();
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));
        arrayListItems.add(new ArrayListItem("Time", "Event", "Location"));

        ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext(), 0, arrayListItems);
        listView.setAdapter(listViewAdapter);

    }
}

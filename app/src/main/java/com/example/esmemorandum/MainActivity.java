package com.example.esmemorandum;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.esmemorandum.adapter.ArrayListItem;
import com.example.esmemorandum.adapter.ListViewAdapter;
import com.example.esmemorandum.bean.Event;
import com.example.esmemorandum.db.MySQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;
    private ArrayList<Event> events;
    private ListViewAdapter listViewAdapter;
    private int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        events = new ArrayList<>();

        mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext(), "ESM.db", null, 2);
        db = mySQLiteOpenHelper.getWritableDatabase();

        listViewAdapter = new ListViewAdapter(getApplicationContext(), 0, getArrayListItems());
        listView.setAdapter(listViewAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        registerForContextMenu(listView);
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                AdapterView.AdapterContextMenuInfo mInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                selectedItemId = mInfo.position;
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.mainactivity_listcontextmenu, menu);
            }
        });
        //TODO click to show details
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cmenu_Delete:
                int id = events.get(selectedItemId).getId();
                if (db.delete("esm", "id=?", new String[]{String.valueOf(id)}) == 0) {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                } else {
                    listViewAdapter.removeItem(selectedItemId);
                    events.remove(selectedItemId);
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.cmenu_Edit:
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                Event e = events.get(selectedItemId);
                intent.putExtra("Event", e);
                startActivityForResult(intent, 2);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            Event event = (Event) data.getSerializableExtra("Event");
            SimpleDateFormat sdf_date = event.getSdf_date();
            SimpleDateFormat sdf_time = event.getSdf_time();
            String startDate = sdf_date.format(event.getStartDate()) + sdf_time.format(event.getStartTime());
            ArrayListItem arrayListItem = new ArrayListItem(startDate, event.getEvent(), event.getLocation());
            String endDate = sdf_date.format(event.getEndDate()) + sdf_time.format(event.getEndTime());
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", event.getEvent());
            contentValues.put("location", event.getLocation());
            contentValues.put("startdate", startDate);
            contentValues.put("enddate", endDate);
            contentValues.put("remarks", event.getRemarks());
            contentValues.put("vibration", event.isVibration());
            contentValues.put("ring", event.isRing());
            int id;
            if ((id = (int) db.insert("esm", null, contentValues)) != -1) {
                listViewAdapter.add(arrayListItem);
                event.setId(id);
                events.add(event);
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 2 && resultCode == 2) {
            Event event = (Event) data.getSerializableExtra("Event");
            SimpleDateFormat sdf_date = event.getSdf_date();
            SimpleDateFormat sdf_time = event.getSdf_time();
            String startDate = sdf_date.format(event.getStartDate()) + sdf_time.format(event.getStartTime());
            ArrayListItem arrayListItem = new ArrayListItem(startDate, event.getEvent(), event.getLocation());
            String endDate = sdf_date.format(event.getEndDate()) + sdf_time.format(event.getEndTime());
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", event.getEvent());
            contentValues.put("location", event.getLocation());
            contentValues.put("startdate", startDate);
            contentValues.put("enddate", endDate);
            contentValues.put("remarks", event.getRemarks());
            contentValues.put("vibration", event.isVibration());
            contentValues.put("ring", event.isRing());
            events.set(selectedItemId, event);
            int id = event.getId();
            if (db.update("esm", contentValues, "id=?", new String[]{String.valueOf(id)}) == 0) {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            } else {
                listViewAdapter.updateItem(selectedItemId, arrayListItem);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainactivity_actionbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Refresh:
                listViewAdapter = new ListViewAdapter(MainActivity.this, 0, getArrayListItems());
                listView.setAdapter(listViewAdapter);
                Toast.makeText(this, "刷新完成", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_Search:
                //TODO search
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<ArrayListItem> getArrayListItems() {
        ArrayList<ArrayListItem> arrayListItems = new ArrayList<>();
        events.clear();
        Cursor cursor = db.query("esm", null, null, null, null, null, "id");
        while (cursor.moveToNext()) {
            Event e = new Event();
            SimpleDateFormat sdf_date = e.getSdf_date();
            SimpleDateFormat sdf_time = e.getSdf_time();
            String event = cursor.getString(cursor.getColumnIndex("event"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
            String startDate = cursor.getString(cursor.getColumnIndex("startdate"));
            String endDate = cursor.getString(cursor.getColumnIndex("enddate"));
            String startTime = startDate.substring(11);
            startDate = startDate.substring(0, 11);
            String endTime = endDate.substring(11);
            endDate = endDate.substring(0, 11);
            ArrayListItem arrayListItem = new ArrayListItem(startDate + " " + startTime, event, location);
            arrayListItems.add(arrayListItem);
            boolean isVibration = Boolean.valueOf(cursor.getInt(cursor.getColumnIndex("vibration")) + "");
            boolean isRing = Boolean.valueOf(cursor.getInt(cursor.getColumnIndex("ring")) + "");
            Date sDate = null, eDate = null, sTime = null, eTime = null;
            try {
                sDate = sdf_date.parse(startDate);
                sTime = sdf_time.parse(startTime);
                eDate = sdf_date.parse(endDate);
                eTime = sdf_time.parse(endTime);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            e = new Event(event, location, sDate, eDate, sTime, eTime, remarks, isVibration, isRing);
            e.setId(cursor.getInt(cursor.getColumnIndex("id")));
            events.add(e);
        }
        return arrayListItems;
    }
}

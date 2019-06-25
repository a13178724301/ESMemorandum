package com.example.esmemorandum;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.esmemorandum.bean.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SubActivity extends AppCompatActivity {
    private EditText editText_Event, editText_Location, editText_Remarks;
    private Switch switch_Vibration, switch_Ring;
    private TextView textView_StartDate, textView_EndDate, textView_StartTime, textView_EndTime;
    private ActionBar actionBar;
    private boolean isEdit;
    private Intent intent;
    private Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        editText_Event = findViewById(R.id.editText_Event);
        editText_Location = findViewById(R.id.editText_Location);
        editText_Remarks = findViewById(R.id.editText_Remarks);
        switch_Ring = findViewById(R.id.switch_Ring);
        switch_Vibration = findViewById(R.id.switch_Vibration);
        textView_StartDate = findViewById(R.id.textView_StartDate);
        textView_EndDate = findViewById(R.id.textView_EndDate);
        textView_StartTime = findViewById(R.id.textView_StartTime);
        textView_EndTime = findViewById(R.id.textView_EndTime);
        intent = getIntent();
        isEdit = false;

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        final SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy.MM.dd");
        final SimpleDateFormat sdf_Time = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        textView_StartDate.setText(sdf_Date.format(date));
        textView_StartTime.setText(sdf_Time.format(date));
        date = new Date(System.currentTimeMillis() + 3600000);
        textView_EndDate.setText(sdf_Date.format(date));
        textView_EndTime.setText(sdf_Time.format(date));

        if (intent.getSerializableExtra("Event") != null) {
            actionBar.setTitle("编辑事件");
            isEdit = true;
            e = (Event) intent.getSerializableExtra("Event");
            editText_Event.setText(e.getEvent());
            editText_Location.setText(e.getLocation());
            editText_Remarks.setText(e.getRemarks());
            if (e.isVibration()) {
                switch_Vibration.setChecked(true);
            } else {
                switch_Vibration.setChecked(false);
            }
            if (e.isRing()) {
                switch_Ring.setChecked(true);
            } else {
                switch_Ring.setChecked(false);
            }
            textView_StartDate.setText(sdf_Date.format(e.getStartDate()));
            textView_StartTime.setText(sdf_Time.format(e.getStartTime()));
            textView_EndDate.setText(sdf_Date.format(e.getEndDate()));
            textView_EndTime.setText(sdf_Time.format(e.getEndTime()));
        }

        textView_StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog;
                if (isEdit) {
                    calendar.setTime(e.getStartDate());
                }
                datePickerDialog = new DatePickerDialog(SubActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                        calendar.setTimeZone(TimeZone.getDefault());
                        textView_StartDate.setText(sdf_Date.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        textView_EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog;
                if (isEdit) {
                    calendar.setTime(e.getEndDate());
                }
                datePickerDialog = new DatePickerDialog(SubActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                        calendar.setTimeZone(TimeZone.getDefault());
                        textView_EndDate.setText(sdf_Date.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        textView_StartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog;
                if (isEdit) {
                    calendar.setTime(e.getStartTime());
                }
                timePickerDialog = new TimePickerDialog(SubActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Date date = new Date(hourOfDay * 60 * 60 * 1000 + minute * 60 * 1000 - 8 * 60 * 60 * 1000);
                        textView_StartTime.setText(sdf_Time.format(date));
                    }
                }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.SECOND), true);
                timePickerDialog.show();
            }
        });

        textView_EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog;
                if (isEdit) {
                    calendar.setTime(e.getEndTime());
                }
                timePickerDialog = new TimePickerDialog(SubActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Date date = new Date(hourOfDay * 60 * 60 * 1000 + minute * 60 * 1000 - 8 * 60 * 60 * 1000);
                        textView_EndTime.setText(sdf_Time.format(date));
                    }
                }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.SECOND), true);
                timePickerDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.subactivity_actionbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!editText_Event.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("是否保存？").setPositiveButton("保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy.MM.dd");
                            SimpleDateFormat sdf_Time = new SimpleDateFormat("HH:mm");
                            String sEvent = editText_Event.getText().toString();
                            String sLocation = editText_Location.getText().toString();
                            String sRemarks = editText_Remarks.getText().toString();
                            boolean isVibration = switch_Vibration.isChecked(), isRing = switch_Ring.isChecked();
                            Date startDate = null, endDate = null, startTime = null, endTime = null;
                            try {
                                startDate = sdf_Date.parse(textView_StartDate.getText().toString());
                                endDate = sdf_Date.parse(textView_EndDate.getText().toString());
                                startTime = sdf_Time.parse(textView_StartTime.getText().toString());
                                endTime = sdf_Time.parse(textView_EndTime.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Event event = new Event(sEvent, sLocation, startDate, endDate, startTime, endTime, sRemarks, isVibration, isRing);
                            if (isEdit) {
                                event.setId(e.getId());
                            }
                            Intent intent = new Intent(SubActivity.this, MainActivity.class);
                            intent.putExtra("Event", event);
                            setResult((isEdit) ? 2 : 1, intent);
                            SubActivity.this.finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SubActivity.this, MainActivity.class);
                            setResult(0, intent);
                            SubActivity.this.finish();
                        }
                    }).create().show();
                } else {
                    Toast.makeText(this, "未保存更改", Toast.LENGTH_SHORT).show();
                    intent = new Intent(SubActivity.this, MainActivity.class);
                    setResult(0, intent);
                    this.finish();
                }
                break;

            case R.id.menu_Save:
                if (editText_Event.getText().toString().equals("")) {
                    Toast.makeText(this, "未填写事件", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy.MM.dd");
                    SimpleDateFormat sdf_Time = new SimpleDateFormat("HH:mm");
                    String sEvent = editText_Event.getText().toString();
                    String sLocation = editText_Location.getText().toString();
                    String sRemarks = editText_Remarks.getText().toString();
                    boolean isVibration = switch_Vibration.isChecked(), isRing = switch_Ring.isChecked();
                    Date startDate = null, endDate = null, startTime = null, endTime = null;
                    try {
                        startDate = sdf_Date.parse(textView_StartDate.getText().toString());
                        endDate = sdf_Date.parse(textView_EndDate.getText().toString());
                        startTime = sdf_Time.parse(textView_StartTime.getText().toString());
                        endTime = sdf_Time.parse(textView_EndTime.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Event event = new Event(sEvent, sLocation, startDate, endDate, startTime, endTime, sRemarks, isVibration, isRing);
                    if (isEdit) {
                        event.setId(e.getId());
                    }
                    intent = new Intent(SubActivity.this, MainActivity.class);
                    intent.putExtra("Event", event);
                    setResult((isEdit) ? 2 : 1, intent);
                    this.finish();
                }
                break;

            case R.id.menu_Reset:
                switch_Vibration.setChecked(false);
                switch_Ring.setChecked(false);
                editText_Location.setText("");
                editText_Event.setText("");
                editText_Remarks.setText("");
                SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat sdf_Time = new SimpleDateFormat("HH:mm");
                Date date = new Date(System.currentTimeMillis());
                textView_StartDate.setText(sdf_Date.format(date));
                textView_StartTime.setText(sdf_Time.format(date));
                date = new Date(System.currentTimeMillis() + 3600000);
                textView_EndDate.setText(sdf_Date.format(date));
                textView_EndTime.setText(sdf_Time.format(date));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

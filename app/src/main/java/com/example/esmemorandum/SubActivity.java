package com.example.esmemorandum;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SubActivity extends AppCompatActivity {
    private EditText editText_Event, editText_Location;
    private Switch switch_Vibration, switch_Ring;
    private TextView textView_StartDate, textView_EndDate, textView_StartTime, textView_EndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        editText_Event = findViewById(R.id.editText_Event);
        editText_Location = findViewById(R.id.editText_Location);
        switch_Ring = findViewById(R.id.switch_Ring);
        switch_Vibration = findViewById(R.id.switch_Vibration);
        textView_StartDate = findViewById(R.id.textView_StartDate);
        textView_EndDate = findViewById(R.id.textView_EndDate);
        textView_StartTime = findViewById(R.id.textView_StartTime);
        textView_EndTime = findViewById(R.id.textView_EndTime);

        //Initialize Date&Time
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        final SimpleDateFormat sdf_Date = new SimpleDateFormat("yyyy年MM月dd日");
        final SimpleDateFormat sdf_Time = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        textView_StartDate.setText(sdf_Date.format(date));
        textView_StartTime.setText(sdf_Time.format(date));
        date = new Date(System.currentTimeMillis() + 3600000);
        textView_EndDate.setText(sdf_Date.format(date));
        textView_EndTime.setText(sdf_Time.format(date));

        //Set Listener
        textView_StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(SubActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(SubActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(SubActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(SubActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
}

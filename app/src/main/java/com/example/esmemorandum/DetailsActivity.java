package com.example.esmemorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esmemorandum.bean.Event;

import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView_Date, textView_Time, textView_Event, textView_Location, textView_Remarks;
    private ImageView imageView_Ring, imageView_Vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent = getIntent();
        textView_Date = findViewById(R.id.textView_Date);
        textView_Time = findViewById(R.id.textView_Time);
        textView_Event = findViewById(R.id.textView_Event);
        textView_Location = findViewById(R.id.textView_Location);
        textView_Remarks = findViewById(R.id.textView_Remarks);
        imageView_Ring = findViewById(R.id.imageView_Ring);
        imageView_Vibration = findViewById(R.id.imageView_Vibration);

        Event event = (Event) intent.getSerializableExtra("Event");
        if (event != null) {
            SimpleDateFormat sdf_date = event.getSdf_date();
            SimpleDateFormat sdf_time = event.getSdf_time();
            String date = sdf_date.format(event.getStartDate()) + "  -  " + sdf_date.format(event.getEndDate());
            String time = sdf_time.format(event.getStartTime()) + "  -  " + sdf_time.format(event.getEndTime());
            textView_Date.setText(date);
            textView_Time.setText(time);
            textView_Event.setText(event.getEvent());
            textView_Location.setText(event.getLocation());
            textView_Remarks.setText(event.getRemarks());
            imageView_Ring.setVisibility((event.isRing()) ? View.VISIBLE : View.INVISIBLE);
            imageView_Vibration.setVisibility((event.isVibration()) ? View.VISIBLE : View.INVISIBLE);
        }
    }
}

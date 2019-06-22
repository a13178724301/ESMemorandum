package com.example.esmemorandum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.esmemorandum.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ArrayListItem> {
    private int resourceId;
    private ArrayList<ArrayListItem> arrayListItems;
    private Context context;

    public ListViewAdapter(@Nullable Context context, int resource, @NonNull ArrayList<ArrayListItem> objects) {
        super(context, resource, objects);
        arrayListItems = objects;
        resourceId = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textView_Time = convertView.findViewById(R.id.textView_Time);
            viewHolder.textView_Event = convertView.findViewById(R.id.textView_Event);
            viewHolder.textView_Location = convertView.findViewById(R.id.textView_Location);
            viewHolder.textView_Time.setText(arrayListItems.get(position).getTime());
            viewHolder.textView_Event.setText(arrayListItems.get(position).getEvent());
            viewHolder.textView_Location.setText(arrayListItems.get(position).getLocation());
            convertView.setTag(viewHolder);
            return convertView;
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.textView_Time.setText(arrayListItems.get(position).getTime());
        viewHolder.textView_Event.setText(arrayListItems.get(position).getEvent());
        viewHolder.textView_Location.setText(arrayListItems.get(position).getLocation());
        return convertView;
    }

    private static class ViewHolder {
        public TextView textView_Time, textView_Event, textView_Location;
    }
}

package com.example.smartremote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ScrollListAdapter extends ArrayAdapter<String> {

    public ScrollListAdapter(@NonNull Context context, ArrayList<String> sensorNames) {
        super(context, R.layout.sensor_item, sensorNames);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View sensorItemView = inflater.inflate(R.layout.sensor_item, parent,false);

        String sensorStringItem = getItem(position);
        TextView sensorNameTextView = sensorItemView.findViewById(R.id.txt_sensor_name);

        sensorNameTextView.setText(sensorStringItem);

        return sensorItemView;

    }
}

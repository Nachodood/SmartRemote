package com.example.smartremote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class DeviceScrollListAdapter extends ArrayAdapter<String> {

    public DeviceScrollListAdapter(@NonNull Context context, String[] deviceNames, String[] deviceLocations) {
        super(context, R.layout.device_item, deviceNames); //TODO Need to add deviceLocations[] etc somehow, maybe through Device objects
    }

    public DeviceScrollListAdapter(@NonNull Context context, ArrayList<String> exampleDeviceInfo) {
        super(context, R.layout.device_item, exampleDeviceInfo); //TODO Need to add deviceLocations[] etc somehow, maybe through Device objects
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater deviceItemInflater = LayoutInflater.from(getContext());
        View customDeviceView = deviceItemInflater.inflate(R.layout.device_item, parent, false);

        String singleDeviceName = getItem(position);

        TextView txtDeviceName = customDeviceView.findViewById(R.id.txt_name);
        TextView txtDeviceRoom = customDeviceView.findViewById(R.id.txt_room);
        ImageView imgDevicePic = customDeviceView.findViewById(R.id.img_device);

        txtDeviceName.setText(singleDeviceName);
        //TODO: txtDeviceRoom.setText();
        imgDevicePic.setImageResource(R.drawable.ic_launcher_foreground); //TODO: Use array

        return customDeviceView;
    }
}

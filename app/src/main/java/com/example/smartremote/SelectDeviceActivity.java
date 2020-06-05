package com.example.smartremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SelectDeviceActivity extends AppCompatActivity {

    TextView m_txtName, m_txtRoom, m_txtCompass;

    String m_name, m_room, tag;

    int m_compass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.d(tag, "No extras");
            return;
        }

        m_name = extras.getString("DeviceName");
        m_txtName = findViewById(R.id.txt_name);
        m_txtName.setText(m_name);

        m_room = extras.getString("DeviceRoom");
        m_txtRoom = findViewById(R.id.txt_room);
        m_txtRoom.setText(m_room);

        m_compass = extras.getInt("CompassX");
        m_txtCompass = findViewById(R.id.txt_compass);
        m_txtCompass.setText("Compass " + m_compass);
    }
}

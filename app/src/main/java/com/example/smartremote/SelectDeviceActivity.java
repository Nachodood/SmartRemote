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

    Bundle m_extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

        m_extras = getIntent().getExtras();
        if (m_extras == null) {
            Log.d(tag, "No extras");
            return;
        }

        setupViews();

    }

    private void setupViews() {

        m_name = m_extras.getString("DeviceName");
        m_txtName = findViewById(R.id.txt_name);
        m_txtName.setText(m_name);

        m_room = m_extras.getString("DeviceRoom");
        m_txtRoom = findViewById(R.id.txt_room);
        m_txtRoom.setText(m_room);

        m_compass = m_extras.getInt("CompassX");
        m_txtCompass = findViewById(R.id.txt_compass);
        m_txtCompass.setText("Compass " + m_compass);
    }
}

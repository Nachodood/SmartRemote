package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectDeviceActivity extends AppCompatActivity {

    TextView m_txtName, m_txtRoom, m_txtCompass;
    Button m_btnSelectDevice;

    String m_name, m_room, tag;

    int m_compass;

    Bundle m_extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

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

        m_btnSelectDevice = findViewById(R.id.btn_select_device);
        m_btnSelectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectDeviceIntent = new Intent(SelectDeviceActivity.this,
                        CalibrateActivity.class);
                startActivity(selectDeviceIntent);
            }
        });
    }
}

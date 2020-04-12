package com.example.smartremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button m_btnConnect, m_btnViewGestures;

    TextView m_txtViewAvailableSensors;

    ListView m_listAvailableSensors;

    List<Sensor> m_deviceSensorsList;
    ArrayList<String> m_StringSensorsList;
    ScrollListAdapter m_customListAdapter;

    Sensor m_currentSensor;
    String m_currentSensorString;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_deviceSensorsList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        m_StringSensorsList = new ArrayList<>();

        int i=0;
        while(i<=m_deviceSensorsList.size())
        {
            m_currentSensor = m_deviceSensorsList.get(i);
            m_currentSensorString = m_currentSensor.getName();
            m_StringSensorsList.add(m_currentSensorString);
            i++;
        }

        m_customListAdapter = new ScrollListAdapter(this, m_StringSensorsList);

        m_listAvailableSensors = findViewById(R.id.list_available_sensors);
        m_listAvailableSensors.setAdapter(m_customListAdapter);

        m_btnConnect = findViewById(R.id.btn_connect);
        m_btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectActivityIntent = new Intent(MainActivity.this,
                        ConnectActivity.class);
                startActivity(connectActivityIntent);
            }
        });

        m_btnViewGestures = findViewById(R.id.btn_view_gestures);
        m_btnViewGestures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewGesturesIntent = new Intent(MainActivity.this,
                        PerformableGesturesActivity.class);
                startActivity(viewGesturesIntent);
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }
}

package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AvailableSensors extends AppCompatActivity {

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
        setContentView(R.layout.activity_available_sensors);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_deviceSensorsList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        m_StringSensorsList = new ArrayList<>();

        int i=0;
        while(i<=m_deviceSensorsList.size()-1)
        {
            m_currentSensor = m_deviceSensorsList.get(i);
            m_currentSensorString = m_currentSensor.getName();
            m_StringSensorsList.add(m_currentSensorString);
            i++;
        }

        m_customListAdapter = new ScrollListAdapter(this, m_StringSensorsList);

        m_listAvailableSensors = findViewById(R.id.list_available_sensors);
        m_listAvailableSensors.setAdapter(m_customListAdapter);


    }
}

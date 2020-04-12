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

    Button  m_btnConnect,
            m_btnViewGestures,
            m_btnSensorTest,
            m_btnAvailableSensors;

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

       //stuff was here

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

        m_btnSensorTest = findViewById(R.id.btn_sensor_test);
        m_btnSensorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorTestIntent = new Intent(MainActivity.this,
                        SensorTest.class);
                startActivity(sensorTestIntent);
            }
        });

        m_btnAvailableSensors = findViewById(R.id.btn_available_sensors);
        m_btnAvailableSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent availableSensorsIntent = new Intent(MainActivity.this,
                        AvailableSensors.class);
                startActivity(availableSensorsIntent);
            }
        });


    }
}

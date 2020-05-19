package com.example.smartremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//https://developer.android.com/reference/android/net/wifi/WifiManager
//https://developer.android.com/training/connect-devices-wirelessly/wifi-direct Create P2P connections with Wi-Fi Direct

//https://developers.google.com/nearby/connections/overview:///////////////////////////////////
    /*Some example use cases:
Collaborative whiteboard: Jot ideas down with nearby participants on a shared virtual whiteboard.
Local multiplayer gaming: Set up a multiplayer game and invite other users nearby to join.
Multi-screen gaming: Use a phone or tablet as a game controller to play games displayed on a nearby large-screen Android device, such as Android TV.
Offline file transfers: Share photos, videos, or any other type of data quickly and without requiring a network connection.
     */
//https://developer.android.com/training/connect-devices-wirelessly
//https://developer.android.com/guide/topics/connectivity/wifi-scan Wi-Fi scanning overview
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button  m_btnViewGestures,
            m_btnSensorTest,
            m_btnCalibrate,
            m_btnAddDevice;

    ImageButton m_btnAvailableSensors,
                m_imgbtnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //stuff was here

        m_imgbtnConnect = findViewById(R.id.imgbtn_connect);
        m_imgbtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectActivityIntent = new Intent(MainActivity.this,
                        ConnectActivity.class);
                startActivity(connectActivityIntent);
            }
        });

        m_btnCalibrate = findViewById(R.id.btn_calibrate);
        m_btnCalibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calibrateActivityIntent = new Intent(MainActivity.this,
                        CalibrateActivity.class);
                startActivity(calibrateActivityIntent);
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

        m_btnAddDevice = findViewById(R.id.btn_add_device);
        m_btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDeviceIntent = new Intent(MainActivity.this,
                        AddDeviceActivity.class);
                startActivity(addDeviceIntent);
            }
        });

        /*
        m_btnViewGestures = findViewById(R.id.btn_view_gestures);
        m_btnViewGestures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewGesturesIntent = new Intent(MainActivity.this,
                        PerformableGesturesActivity.class);
                startActivity(viewGesturesIntent);
            }
        });



        m_btnAvailableSensors = findViewById(R.id.imgbtn_list_sensors);
        m_btnAvailableSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent availableSensorsIntent = new Intent(MainActivity.this,
                        AvailableSensors.class);
                startActivity(availableSensorsIntent);

            }
        });
        */

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

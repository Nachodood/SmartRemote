package com.example.smartremote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    TextView m_wifiStatusText;
    Switch m_wifiStatusSwitch;

    WifiManager m_wifiManager;

    SensorManager m_sensorManager;
    Sensor m_sensorAccel, m_sensorGyro; //TODO altitude

    List<Sensor> m_deviceSensorsList;
    ArrayList<Sensor> m_requiredSensorsList;

    Button  m_btnManageDevicesActivity;
    FloatingActionButton m_fabAddDevice;

    //TODO: I need to add onResume(), onResume() etc. See SensorTest.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //TODO: Pressure/barometer
        m_sensorAccel = m_sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //TODO: Gyroscope or orientation? Orientation is probably a soft sensor
        m_sensorGyro = m_sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        deviceHasSensors();
        setupButtons();

        /*m_btnSensorTest = findViewById(R.id.btn_sensor_test);
        m_btnSensorTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorTestIntent = new Intent(MainActivity.this,
                        SensorTest.class);
                startActivity(sensorTestIntent);
            }
        });
        */

        m_btnManageDevicesActivity = findViewById(R.id.btn_manage_devices);
        m_btnManageDevicesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageDevicesActivityIntent = new Intent(MainActivity.this,
                        ManageDevicesActivity.class);
                startActivity(manageDevicesActivityIntent);
            }
        });

        m_fabAddDevice = findViewById(R.id.fab_add_device);
        m_fabAddDevice.setOnClickListener(new View.OnClickListener() {
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

        m_wifiStatusText = findViewById(R.id.txt_connect_status);
        m_wifiStatusSwitch = findViewById(R.id.switch_wifi);
        m_wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        //Sync device status and switch
        switchWifiSetup();

        m_wifiStatusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked) {
                    m_wifiStatusText.setText("Wifi: On");
                    EnableWiFi();
                } else {
                    m_wifiStatusText.setText("Wifi: Of");
                    DisableWiFi();
                }
            }
        });

    }

    private void setupButtons() {

        m_btnManageDevicesActivity = findViewById(R.id.btn_manage_devices);
        m_btnManageDevicesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageDevicesActivityIntent = new Intent(MainActivity.this,
                        ManageDevicesActivity.class);
                startActivity(manageDevicesActivityIntent);
            }
        });

        m_fabAddDevice = findViewById(R.id.fab_add_device);
        m_fabAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDeviceIntent = new Intent(MainActivity.this,
                        AddDeviceActivity.class);
                startActivity(addDeviceIntent);
            }
        });

    }

    //Check if device has all sensors
    //Return if required sensor is unavailable, give notification and limit functions/features
    private void deviceHasSensors() {

        m_deviceSensorsList = m_sensorManager.getSensorList(Sensor.TYPE_ALL);
        m_requiredSensorsList = new ArrayList<Sensor>();

        m_requiredSensorsList.add(m_sensorAccel);
        m_requiredSensorsList.add(m_sensorGyro);

        if (m_deviceSensorsList.contains(m_sensorAccel)) {
            //Toast.makeText(getApplicationContext(),"m_sensorAccel",Toast.LENGTH_SHORT).show();
        }

        if (m_deviceSensorsList.contains(m_sensorGyro)) {
            //Toast.makeText(getApplicationContext(),"m_sensorGyro",Toast.LENGTH_SHORT).show();
        }
        if (m_deviceSensorsList.contains(m_sensorGyro)) {
            //Toast.makeText(getApplicationContext(),"m_sensorGyro",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

     //Sync the wifi switch button to the current state of the device's wifi
    public void switchWifiSetup(){
        if(m_wifiManager.isWifiEnabled()){
            m_wifiStatusText.setText("Wifi: ON");
            m_wifiStatusSwitch.setChecked(true);
        }
        else
        {
            m_wifiStatusText.setText("Wifi: Off");
            m_wifiStatusSwitch.setChecked(false);
        }
    }

    public void EnableWiFi(){ m_wifiManager.setWifiEnabled(true); }
    public void DisableWiFi(){
        m_wifiManager.setWifiEnabled(false);
    }
}

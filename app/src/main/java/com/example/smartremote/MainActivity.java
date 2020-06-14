package com.example.smartremote;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

//https://developer.android.com/reference/android/net/wifi/WifiManager
//https://developer.android.com/training/connect-devices-wirelessly/wifi-direct Create P2P connections with Wi-Fi Direct

//https://developers.google.com/nearby/connections/overview
//https://developer.android.com/training/connect-devices-wirelessly
//https://developer.android.com/guide/topics/connectivity/wifi-scan Wi-Fi scanning overview

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView m_wifiStatusText;
    Switch m_wifiStatusSwitch;
    Button  m_btnManageDevicesActivity;
    FloatingActionButton m_fabAddDevice;
    ListView m_listAvailableDevices, m_listUnavailableDevices;

    WifiManager m_wifiManager;

    SensorManager m_sensorManager;
    Sensor m_sensorAccel, m_sensorGyro; //TODO altitude

    List<Sensor> m_deviceSensorsList;
    ArrayList<Sensor> m_requiredSensorsList;

    ListAdapter m_customDeviceAdapter, m_customUnavailableAdapter;

    //TODO: I need to add onResume(), onResume() etc. See SensorTest.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupSensors();
        deviceHasSensors();
        setupView();
        setupListeners();

        m_wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        //Sync device status and switch
        switchWifiSetup();

    }

    /////////////////////////////////////////////////////// WIFI STUFF ///////////////////////////////////////////////////////

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
    public void DisableWiFi(){ m_wifiManager.setWifiEnabled(false);}

    /////////////////////////////////////////////////////// VIEW STUFF ///////////////////////////////////////////////////////

    private void setupView() {

        /* TODO: Remove button */
        m_btnManageDevicesActivity = findViewById(R.id.btn_manage_devices);

        m_fabAddDevice = findViewById(R.id.fab_add_device);

        m_wifiStatusText = findViewById(R.id.txt_connect_status);
        m_wifiStatusSwitch = findViewById(R.id.switch_wifi);

    }

    /////////////////////////////////////////////////////// SENSORS /////////////////////////////////////////////////////

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

    private void setupSensors() {
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //TODO: Pressure/barometer
        if (m_sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            m_sensorAccel = m_sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else
        {
            //TODO: Dialogue popup
        }
        //TODO: Gyroscope or orientation? Orientation is probably a soft sensor
        m_sensorGyro = m_sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    /////////////////////////////////////////////////////// LISTVIEWS /////////////////////////////////////////////////////

    //////////////////////////////////////////////  LISTENERS   //////////////////////////////////////////////

    private void setupListeners() {

        /* TODO: Remove btnManageDevices button and listener
                 Assign edit details listeners to both lists to replace manage devices purpose
         */
        m_btnManageDevicesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageDevicesActivityIntent = new Intent(MainActivity.this,
                        ManageDevicesActivity.class);
                startActivity(manageDevicesActivityIntent);
            }
        });

        m_fabAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDeviceIntent = new Intent(MainActivity.this,
                        AddDeviceActivity.class);
                startActivity(addDeviceIntent);
            }
        });

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
}

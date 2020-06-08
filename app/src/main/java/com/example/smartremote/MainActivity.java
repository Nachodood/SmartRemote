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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView m_wifiStatusText;
    Switch m_wifiStatusSwitch;
    Button  m_btnManageDevicesActivity;
    FloatingActionButton m_fabAddDevice;
    ListView m_listAvailableDevices;

    WifiManager m_wifiManager;

    SensorManager m_sensorManager;
    Sensor m_sensorAccel, m_sensorGyro; //TODO altitude

    List<Sensor> m_deviceSensorsList;
    ArrayList<Sensor> m_requiredSensorsList;

    ListAdapter m_customDeviceAdapter;

    //TODO: I need to add onResume(), onResume() etc. See SensorTest.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupSensors();
        deviceHasSensors();
        setupView();
        setupScrlList();

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

        m_wifiStatusText = findViewById(R.id.txt_connect_status);
        m_wifiStatusSwitch = findViewById(R.id.switch_wifi);

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

        m_listAvailableDevices = findViewById(R.id.list_available_devices);

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

    /////////////////////////////////////////////////////// LISTVIEW /////////////////////////////////////////////////////
    //ListView has been setup in setupView()
    private void setupScrlList() {
        //Of course, the list would be populated from persistant storage.
        //Possibly use a list of Device Objects?
        //ArrayList<Device> exampleDevices = new ArrayList<Device>();

        ArrayList<String> arrayListExampleDeviceNames = new ArrayList<>();
        arrayListExampleDeviceNames.add("Echo");
        arrayListExampleDeviceNames.add( "Alexa");
        arrayListExampleDeviceNames.add("Bulb");
        arrayListExampleDeviceNames.add( "SmartTV");

        ArrayList<String> arrayListExampleRooms = new ArrayList<>();
        arrayListExampleRooms.add("Bedroom");
        arrayListExampleRooms.add( "Living room");
        arrayListExampleRooms.add("Kitchen");
        arrayListExampleRooms.add( "Bathroom");

//        ListAdapter customDeviceAdapter = new DeviceScrollListAdapter(this, arrayListExampleDeviceNames, arrayListExampleRooms);

        ArrayList<String> exampleDeviceInfo = new ArrayList<>();
        int i = 0;
        while(i<arrayListExampleDeviceNames.size()){
            exampleDeviceInfo.add(arrayListExampleDeviceNames.get(i) + " " + arrayListExampleRooms.get(i) );
            i++;
        }

        m_customDeviceAdapter = new DeviceScrollListAdapter(this, exampleDeviceInfo);

       /* Device alexa = new Device("alexa", "Kitchen", 67, 1000);
        Device smartTV = new Device("smartTV", "LivingRoom", 22, 1000);
        Device Bulb = new Device("Bulb", "Bedroom", 127, 1005);
        Device bulb2 = new Device("Bulb2", "MasterBedroom", 359, 999);
        exampleDevices.add(alexa);
        exampleDevices.add(smartTV);
        exampleDevices.add(Bulb);
        exampleDevices.add(bulb2);
        exampleDevices.toArray();
        exampleDevices.toString();
        ListAdapter customDeviceAdapter = new DeviceScrollListAdapter(this, exampleDevices); */

        m_listAvailableDevices.setAdapter(m_customDeviceAdapter);
    }
}

package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
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
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AddDeviceActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Compass";

    private SensorManager m_sensorManager;
    private Sensor m_altitudeSensor;
    private List<Sensor> m_sensorsList;

    EditText m_editText_name, m_editText_room;
    //TextView m_txtCompassValues;
    Button m_btnBtnSaveDeviceValues;
    TextView m_txtCompassValues;
    
    int m_CompassXValue, m_altitudeValue;
    String m_nameOfDevice, m_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        setupView();

        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        setupSensors();

    }

    private void setupView() {

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        //The user doesn't need to see the compass values
        m_txtCompassValues = findViewById(R.id.m_txtCompassValues);

        m_btnBtnSaveDeviceValues = findViewById(R.id.btn_save_device);
        m_btnBtnSaveDeviceValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDeviceValues();
            }
        });

        m_editText_name = findViewById(R.id.editText_name);

        m_editText_room = findViewById(R.id.editText_room);

    }

    private void setupSensors() {
        //TODO:Implement sensors list, either here or at main, maybe both
        //Get from AvailableSensors.java

        Sensor m_compassSensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        /* TODO:Finish this */
        m_altitudeSensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        m_sensorManager.registerListener(mListener, m_compassSensor, 100);
    }

    private void saveDeviceValues() {
        m_nameOfDevice = m_editText_name.getText().toString();
        m_room = m_editText_room.getText().toString();
        //m_CompassXValue;

        Intent i = new Intent(this, SelectDeviceActivity.class);
        i.putExtra("DeviceName", m_nameOfDevice);
        i.putExtra("DeviceRoom", m_room);
        i.putExtra("CompassX", m_CompassXValue);
        //i.putExtra("Altitude", m_altitudeValue);
        startActivity(i);
    }

    private final SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //mValues = event.values;

            m_CompassXValue = (int) event.values[0];

            m_txtCompassValues.setText("X axis: " +  m_CompassXValue/*+ ", " + event.values[1] + ", " + event.values[2]*/);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

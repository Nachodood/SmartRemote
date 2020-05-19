package com.example.smartremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AddDeviceActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Compass";
    private SensorManager m_sensorManager;
    private Sensor m_sensor;
    float[] mValues;
    TextView m_txtCompassValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        m_txtCompassValues = findViewById(R.id.txt_compass_value);

        m_sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        m_sensorManager.registerListener(mListener, m_sensor, 100);
    }

    private final SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            m_txtCompassValues.setText(event.values[0] + ", " + event.values[1] + ", " + event.values[2]);

            //mValues = event.values;
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

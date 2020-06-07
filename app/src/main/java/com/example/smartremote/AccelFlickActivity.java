package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class AccelFlickActivity extends AppCompatActivity implements SensorEventListener {

        private SensorManager m_sensorManager;

    private long lastUpdate = 0;
        float highestZ = 0;
        float lowestZ = 0;

        TextView m_txtView_Accel,
                m_txt_highestZ,
                m_txt_lowestZ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_accel_flick);

            m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

            setupViews();
            setupSensors();

        }

        private void setupSensors() {
            //linear acceleration = acceleration - acceleration due to gravity
            Sensor sensorAccel = m_sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            Objects.requireNonNull(m_sensorManager).registerListener(this, sensorAccel, SensorManager.SENSOR_DELAY_NORMAL);
        }

        //////////////////////////////////////////////////////////////// VIEWS ////////////////////////////////////////////////////////////////

        private void setupViews() {
            // Get a support ActionBar corresponding to this toolbar
            ActionBar ab = getSupportActionBar();
            // Enable the Up button
            assert ab != null;
            ab.setDisplayHomeAsUpEnabled(true);

            m_txtView_Accel = findViewById(R.id.txt_accel);
            m_txt_highestZ = findViewById(R.id.txt_highest_Z);
            m_txt_lowestZ = findViewById(R.id.txt_lowest_z);
        }

    //////////////////////////////////////////////////////////////// SENSORS ////////////////////////////////////////////////////////////////

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //Sensor mySensor = sensorEvent.sensor;

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            if(z>highestZ){
                highestZ = z;
            }

            if(z<lowestZ){
                lowestZ = z;
            }

            m_txt_lowestZ.setText("Lowest z: " + lowestZ);

            m_txt_highestZ.setText("Highest z: " + highestZ);
            m_txtView_Accel.setText("x: " + (int) x + " y: " + (int) y + " z: " + (int) z);
        /*if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                m_txtView_Accel.setText((int) x);
            }
        }*/


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalibrateDownUpActivity extends AppCompatActivity implements SensorEventListener {

    private Boolean isInCalMode = false;

    private TextView m_txtIsComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate_down_up);

        setupSensor();
        setupView();

    }

    private void setupView() {

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        m_txtIsComplete = findViewById(R.id.txt_is_complete);

        Button btnStart = findViewById(R.id.btn_cal_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnStop = findViewById(R.id.btn_cal_end);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        m_txtIsComplete = findViewById(R.id.txt_is_complete);

    }

    private void setupSensor() {
        SensorManager m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //linear acceleration = acceleration - acceleration due to gravity
        assert m_sensorManager != null;
        Sensor mSensorAccel = m_sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        m_sensorManager.registerListener(this, mSensorAccel, SensorManager.SENSOR_DELAY_NORMAL);

        m_sensorManager.registerListener(this,
                m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                float z = sensorEvent.values[2];
                float m_ZFlickThreshold = 10;
                if (z > m_ZFlickThreshold) {
                    m_txtIsComplete.setText("Flick complete");
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

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

    private float m_ZFlickThreshold = 10;
    private Boolean isInCalMode = false;

    private TextView m_txtIsComplete;
    private Button btnStart, btnStop;

    private SensorManager m_sensorManager;
    private Sensor mSensorAccel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate_down_up);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        setupSensorStuff();
        setupView();

    }

    private void setupSensorStuff() {
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //linear acceleration = acceleration - acceleration due to gravity
        mSensorAccel = m_sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        m_sensorManager.registerListener(this, mSensorAccel, SensorManager.SENSOR_DELAY_NORMAL);

        m_sensorManager.registerListener(this,
                m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                m_sensorManager.SENSOR_DELAY_GAME);
    }

    private void setupView() {

        m_txtIsComplete = findViewById(R.id.txt_is_complete);

        btnStart = findViewById(R.id.btn_cal_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnStop = findViewById(R.id.btn_cal_end);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        m_txtIsComplete = findViewById(R.id.txt_is_complete);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        synchronized (this) {
            switch (sensorEvent.sensor.getType()){

                case Sensor.TYPE_LINEAR_ACCELERATION:

                    float z = sensorEvent.values[2];
                    if(z>m_ZFlickThreshold){
                        m_txtIsComplete.setText("Flick complete");
                    }
                    break;

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

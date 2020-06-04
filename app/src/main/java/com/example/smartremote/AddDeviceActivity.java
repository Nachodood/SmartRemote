package com.example.smartremote;

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

public class AddDeviceActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Compass";

    private SensorManager m_sensorManager;
    private Sensor m_compassSensor, m_altitudeSensor;

    float[] mValues;
    int m_CompassXValue;

    //TextView m_txtCompassValues;
    Button m_saveCompassXValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        //TODO:Implement sensors list, either here or at main, maybe both

        //The user doesn't need to see the compass values
        //m_txtCompassValues = findViewById(R.id.txt_compass_value);

        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_compassSensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        //TODO:Finish this
        m_altitudeSensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);


        m_sensorManager.registerListener(mListener, m_compassSensor, 100);

        m_saveCompassXValue = findViewById(R.id.btn_save_device);
        m_saveCompassXValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private final SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //mValues = event.values;

            m_CompassXValue = (int) event.values[0];

            //m_txtCompassValues.setText("X axis: " +  m_CompassXValue/*+ ", " + event.values[1] + ", " + event.values[2]*/);

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

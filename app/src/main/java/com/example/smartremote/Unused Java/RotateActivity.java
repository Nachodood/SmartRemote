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

/* Rotation vector:
Uses the Accelerometer, Magnetometer,and Gyroscope
and reports the orientation of the device
relative to the East-North-Up coordinates frame.
ØIt uses a gyroscope as the main orientation change
input, and uses the accelerometer and magnetometer
input to make up for gyroscope drift.
 */

public class RotateActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager msensorManager;
    private Sensor mSensorAccel;
    private Sensor mSensorOrientation;
    private float m_ZFlickThreshold = 10;
    private TextView m_txtRotateOutput;

    private float orientx, orienty, orientz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        m_txtRotateOutput = findViewById(R.id.txt_rotate_output);

        msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //linear acceleration = acceleration - acceleration due to gravity
        mSensorAccel = msensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        msensorManager.registerListener(this, mSensorAccel, SensorManager.SENSOR_DELAY_NORMAL);

        msensorManager.registerListener(this,
                msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                msensorManager.SENSOR_DELAY_GAME);

        //m_txtIsComplete.setProgress(0);
        //m_txtIsComplete.setMax(100);
    }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            synchronized (this) {

                switch (sensorEvent.sensor.getType()) {

                    case Sensor.TYPE_ORIENTATION:
                        ;
                        orientx = sensorEvent.values[0];
                        orienty = sensorEvent.values[1];
                        orientz = sensorEvent.values[2];

                        //m_txtIsComplete.setText("Orientationx:"+Float.toString(sensorEvent.values[0]));

                        if (orientx > 45 && 50 > orientx) {
                            m_txtRotateOutput.setText("Rotated right");
                        } else if (orientx > 280 && 290 > orientx) {
                            m_txtRotateOutput.setText("Rotated left");
                        }
                        /*else if (orienty <-10 && orienty > -40)
                        {
                            m_txtRotateOutput.setText("Rotated forward");
                        }*/
                }

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
}

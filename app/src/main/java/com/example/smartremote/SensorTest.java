package com.example.smartremote;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SensorTest extends Activity implements SensorEventListener  {
    SensorManager sensorManager = null;
    //for accelerometer values
    TextView outputX;
    TextView outputY;
    TextView outputZ;
    //for orientation values
    TextView outputX2;
    TextView outputY2;
    TextView outputZ2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        setContentView(R.layout.activity_sensor_test);

//just some textviews, for data output
        outputX =  findViewById(R.id.TextView01);
        outputY =  findViewById(R.id.TextView02);
        outputZ =  findViewById(R.id.TextView03);
        outputX2 = findViewById(R.id.TextView04);
        outputY2 = findViewById(R.id.TextView05);
        outputZ2 = findViewById(R.id.TextView06);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                sensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                sensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sensorManager.unregisterListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
    }

    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    outputX.setText("Accelerationx:"+Float.toString(event.values[0]));
                    outputY.setText("Accelerationy:"+Float.toString(event.values[1]));
                    outputZ.setText("Accelerationz:"+Float.toString(event.values[2]));
                    break;
                case Sensor.TYPE_ORIENTATION:
                    outputX2.setText("Orientationx:"+Float.toString(event.values[0]));
                    outputY2.setText("Orientationy:"+Float.toString(event.values[1]));
                    outputZ2.setText("Orientationz:"+Float.toString(event.values[2]));
                    break;
            }
        }

        /*Sensor.TYPE_ROTATION_VECTOR;
        Sensor.TYPE_GAME_ROTATION_VECTOR;
        Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR; */

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}


package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ManageDevicesActivity extends AppCompatActivity {

    /* TODO: What do
         have a manage devices activity displaying a list of all connected devices
         or one activity that manages one device at a time??? */

    Button m_btnCalibrate, m_btnEditDetails;
    TextView m_txtDeviceInformation;

    String m_deviceInformation;

    Bundle m_extras;

    public static final String TAG = "ManageDevicesExtras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_devices);

        m_extras = getIntent().getExtras();
        if (m_extras == null) {
            Log.d(TAG, "No extras");
            setupAlllDevicesList();
            return;
        }

        setupView();

        setupListeners();

    }

    /////////////////////////////////////////////// VIEW ///////////////////////////////////////////////

    private void setupView() {

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        m_btnCalibrate = findViewById(R.id.btn_cal_gestures);

        //TODO Device objects etc.
        m_btnEditDetails = findViewById(R.id.btn_edit_details);

        m_txtDeviceInformation = findViewById(R.id.txt_device_information);
        m_deviceInformation = m_extras.getString("DeviceInformation");
        m_txtDeviceInformation.setText(m_deviceInformation);
    }
    /////////////////////////////////////////////// LISTENERS ///////////////////////////////////////////////

    private void setupListeners() {

        m_btnCalibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calibrateActivityIntent = new Intent(ManageDevicesActivity.this,
                        CalibrateActivity.class);
                startActivity(calibrateActivityIntent);
            }
        });

        m_btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editDetailsActivityIntent = new Intent(ManageDevicesActivity.this,
                        AddDeviceActivity.class);
                startActivity(editDetailsActivityIntent);
            }
        });
    }

    private void setupAlllDevicesList(){

    }
}

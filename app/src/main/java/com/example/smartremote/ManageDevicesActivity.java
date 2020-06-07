package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageDevicesActivity extends AppCompatActivity {

    Button m_btnCalibrate, m_btnEditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_devices);

        setupView();
    }

    private void setupView() {

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        m_btnCalibrate = findViewById(R.id.btn_cal_gestures);
        m_btnCalibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calibrateActivityIntent = new Intent(ManageDevicesActivity.this,
                        CalibrateActivity.class);
                startActivity(calibrateActivityIntent);
            }
        });

        //TODO Device objects etc.
        m_btnEditDetails = findViewById(R.id.btn_edit_details);
        m_btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editDetailsActivityIntent = new Intent(ManageDevicesActivity.this,
                        AddDeviceActivity.class);
                startActivity(editDetailsActivityIntent);
            }
        });

    }
}

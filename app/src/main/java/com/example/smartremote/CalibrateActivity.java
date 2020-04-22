package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalibrateActivity extends AppCompatActivity {

    Button m_btnClockwise,
            m_btnAntiClockwise,
            m_btnFlickForward,
            m_btnFlickBackward,
            m_btnSHake,
            m_btnUpDown,
            m_btnDownUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        m_btnClockwise = findViewById(R.id.btn_calib_clock);
        m_btnClockwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        m_btnAntiClockwise = findViewById(R.id.btn_calib_anti);


        m_btnFlickForward = findViewById(R.id.btn_calib_flick_forward);


        m_btnFlickBackward = findViewById(R.id.btn_calib_flick_back);



        m_btnSHake = findViewById(R.id.btn_calib_shake);


        m_btnUpDown = findViewById(R.id.btn_up_down);
        m_btnUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        m_btnDownUp = findViewById(R.id.btn_calib_down_up);
        m_btnDownUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calibDownUpIntent = new Intent(CalibrateActivity.this,
                        CalibrateDownUpActivity.class);
                startActivity(calibDownUpIntent);
            }
        });
    }
}

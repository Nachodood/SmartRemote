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

        setupButtons();



    }

    private void setupButtons() {

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

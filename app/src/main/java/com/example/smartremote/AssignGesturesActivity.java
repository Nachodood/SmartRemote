package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AssignGesturesActivity extends AppCompatActivity {

    Button m_btnDownUp,
            m_btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_gestures);

        setupView();

    }

    private void setupView() {

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        m_btnComplete = findViewById(R.id.btn_complete);
        m_btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent completeIntent = new Intent(AssignGesturesActivity.this,
                        MainActivity.class);
                startActivity(completeIntent);
            }
        });

        m_btnDownUp = findViewById(R.id.btn_calib_down_up);
        m_btnDownUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calibDownUpIntent = new Intent(AssignGesturesActivity.this,
                        CalibrateDownUpActivity.class);
                startActivity(calibDownUpIntent);
            }
        });

    }
}

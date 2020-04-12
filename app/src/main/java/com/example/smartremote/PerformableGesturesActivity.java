package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PerformableGesturesActivity extends AppCompatActivity {

    Button m_btnAccelerationFlick,
            m_btnOrientationLeftRight,
            m_btnSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performable_gestures);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        m_btnAccelerationFlick = findViewById(R.id.btn_accel);
        m_btnAccelerationFlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accelerationFlickIntent = new Intent(PerformableGesturesActivity.this,
                        AccelFlickActivity.class);
                startActivity(accelerationFlickIntent);
            }
        });

        m_btnOrientationLeftRight = findViewById(R.id.btn_orient);

        m_btnSwipe = findViewById(R.id.btn_swipe);

    }
}

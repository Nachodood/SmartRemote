package com.example.smartremote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {

    TextView m_wifiStatusText;
    Switch m_wifiStatusSwitch;
    WifiManager m_wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        m_wifiStatusText = findViewById(R.id.txt_wifi_status);
        m_wifiStatusSwitch = findViewById(R.id.switch_wifi);
        m_wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        switchWifiSetup();

        m_wifiStatusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked) {
                    m_wifiStatusText.setText("Wifi: On");
                    EnableWiFi();
                } else {
                    m_wifiStatusText.setText("Wifi: Of");
                    DisableWiFi();
                }
            }
        });


    }

    public void switchWifiSetup(){
        if(m_wifiManager.isWifiEnabled()){
            m_wifiStatusText.setText("Wifi: ON");
        }
        else
        {
            m_wifiStatusText.setText("Wifi: Off");
        }
    }

    public void EnableWiFi(){

        m_wifiManager.setWifiEnabled(true);
    }
    public void DisableWiFi(){
        m_wifiManager.setWifiEnabled(false);
    }
}

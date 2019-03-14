package com.example.broadcastrdimput;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private DimputReceiver dimasReceiver = new DimputReceiver();
    private ComponentName componentName;
    private PackageManager packageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBroadcast = (Button) findViewById(R.id.btnBroadcast);
        componentName = new ComponentName(this,DimputReceiver.class);
        packageManager = getPackageManager();

        LocalBroadcastManager.getInstance(this).registerReceiver(dimasReceiver,
                new IntentFilter(DimputReceiver.ACTION_DIMPUT_BROADCAST));

        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDimputBroadcast();
            }
        });
    }

    @Override
    protected void onStart() {
        packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        super.onStart();
    }

    private void sendDimputBroadcast(){
        Intent bcIntent = new Intent(DimputReceiver.ACTION_DIMPUT_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(bcIntent);
    }

    @Override
    protected void onStop() {
        packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(dimasReceiver);
        super.onDestroy();
    }
}

package com.example.broadcastrdimput;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DimputReceiver extends BroadcastReceiver {
    final static String ACTION_DIMPUT_BROADCAST = "com.example.broadcastrdimput.ACTION_DIMPUT_BROADCAST";

    public DimputReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intenAction = intent.getAction();
        String msg = null;

        switch (intenAction){
            case Intent.ACTION_POWER_CONNECTED:
                msg = "Dimulai Dari 0 ya Kak !";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                msg = "Terimakasih :)";
                break;
            case ACTION_DIMPUT_BROADCAST:
                msg = "Broadcast Dimput Diterima !";
                break;
        }

        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}

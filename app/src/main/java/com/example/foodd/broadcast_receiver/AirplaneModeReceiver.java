package com.example.foodd.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Airplane Mode disabled", Toast.LENGTH_SHORT).show();
        }
    }
}

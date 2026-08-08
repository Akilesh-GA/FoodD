package com.example.foodd.broadcast_receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodd.R;

public class BroadcastReceiverMain extends AppCompatActivity {
    AirplaneModeReceiver airplaneModeReceiver = new AirplaneModeReceiver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver_test);
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeReceiver, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(airplaneModeReceiver);
    }
}

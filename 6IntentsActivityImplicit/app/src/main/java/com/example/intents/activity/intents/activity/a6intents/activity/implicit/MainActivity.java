package com.example.intents.activity.intents.activity.a6intents.activity.implicit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Prueba onCreate", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(" Prueba onStart", "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(" Prueba onResume", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("Prueba onPause", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(" Prueba onRestart", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("Prueba onStop", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Prueba onDestroy", "onDestroy");
    }
}

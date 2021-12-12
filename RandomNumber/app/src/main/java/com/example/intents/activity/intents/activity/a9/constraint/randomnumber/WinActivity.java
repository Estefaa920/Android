package com.example.intents.activity.intents.activity.a9.constraint.randomnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);



        Intent intent = getIntent();
        int numberFinal= intent.getIntExtra("Numero1", 0);

        TextView numbersFinalsWin =(TextView) findViewById(R.id.result);
        numbersFinalsWin.setText(String.format("El numero acertado es:" + numberFinal, " FELICIDADES!!"));

    }


    public void goToMain(View view){
        Intent intent = new Intent(WinActivity.this, MainActivity.class);
        startActivity(intent);
    }


}

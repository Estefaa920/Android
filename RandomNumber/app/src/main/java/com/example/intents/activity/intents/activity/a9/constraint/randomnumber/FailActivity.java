package com.example.intents.activity.intents.activity.a9.constraint.randomnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);


        Intent intent = getIntent();
        int numberFinal= intent.getIntExtra("Numero1", 0);
        int randomNumber= intent.getIntExtra("Numero2", 0);

        TextView numbersFinals =(TextView) findViewById(R.id.resultFail);

        numbersFinals.setText(String.format("ERROR:\n" + "El numero fallado es: " + numberFinal + " y el número aleatorio que había que adivinar era:"+ randomNumber));


}


    public void goToMain(View view){
        Intent intent = new Intent(FailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
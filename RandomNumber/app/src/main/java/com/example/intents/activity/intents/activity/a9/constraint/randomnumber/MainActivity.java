package com.example.intents.activity.intents.activity.a9.constraint.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    public static int numberFinal;
    public static int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button1);

       button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                EditText numberUser =(EditText) findViewById(R.id.textInput);




                    numberFinal = Integer.parseInt(numberUser.getText().toString());


                    if (numberFinal == randomNumber) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, WinActivity.class);
                        intent.putExtra("Numero1", numberFinal);
                        intent.putExtra("Numero2", randomNumber);
                        startActivity(intent);


                    } else {

                        Intent intent = new Intent(MainActivity.this, FailActivity.class);

                        intent.putExtra("Numero1", numberFinal);
                        intent.putExtra("Numero2", randomNumber);
                        startActivity(intent);

                    }
                }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        randomNumber = (int) (Math.random() * 9);

        Toast.makeText(this, String.valueOf(randomNumber), Toast.LENGTH_SHORT).show();
    }
}



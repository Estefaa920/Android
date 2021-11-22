package com.example.intents.activity.explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               EditText firstNumberEdit =(EditText) findViewById(R.id.firstNumberInput);
                EditText secondNumberEdit =(EditText) findViewById(R.id.secondNumberInput);

                Integer firstNumber= Integer.parseInt(firstNumberEdit.getText().toString());
                Integer secondNumber= Integer.parseInt(secondNumberEdit.getText().toString());

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);

                intent.putExtra("Num1", firstNumber);
                intent.putExtra("Num2", secondNumber);
                startActivity(intent);
            }
        });
    }
}
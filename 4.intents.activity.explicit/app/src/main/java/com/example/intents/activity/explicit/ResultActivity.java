package com.example.intents.activity.explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Integer fristNumber= intent.getIntExtra("Num1", 0);
        Integer secondNumber= intent.getIntExtra("Num2", 0);

        TextView resultTextView = (TextView) findViewById((R.id.result));
        resultTextView.setText(String.format("%d", (fristNumber+secondNumber)));
    }
}
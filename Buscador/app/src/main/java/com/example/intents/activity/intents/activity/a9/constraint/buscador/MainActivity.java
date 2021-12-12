package com.example.intents.activity.intents.activity.a9.constraint.buscador;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchQuery = findViewById(R.id.userInput);
        String searchQueryFinal = searchQuery.getText().toString();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String url = null;
                try {
                    url = URLEncoder.encode(searchQuery.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Uri uri = Uri.parse("http://www.google.com/search?q=" +url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }

        });


    }
}
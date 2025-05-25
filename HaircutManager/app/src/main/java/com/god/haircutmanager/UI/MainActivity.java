package com.god.haircutmanager.UI;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.god.haircutmanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button userManagerButton = findViewById(R.id.userManagerButton);
        Button customerManagerButton = findViewById(R.id.customerManagerButton);
        Button hairStyleManagerButton = findViewById(R.id.hairStyleManagerButton);

        userManagerButton.setOnClickListener(view-> {
            startActivity(new Intent(MainActivity.this, UserManagerActivity.class));
        });

        customerManagerButton.setOnClickListener(view-> {
            startActivity(new Intent(MainActivity.this, CustomerManagerActivity.class));
        });

        hairStyleManagerButton.setOnClickListener(view-> {
            startActivity(new Intent(MainActivity.this, HairStyleManagerActivity.class));
        });
    }
}
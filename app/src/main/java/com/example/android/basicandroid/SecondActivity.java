package com.example.android.basicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "com.example.android.basicandroid.SecondActivity - personName";
    private static final String EXTRA_AGE = "com.example.android.basicandroid.SecondActivity - age";

    private String name;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        extractDataFromIntent();
        setUpBackToMainActivityButton();
    }

    private void extractDataFromIntent() {
        // note getIntent();
        Intent intent = getIntent();
        name = intent.getStringExtra(EXTRA_NAME);
        age = intent.getIntExtra(EXTRA_AGE, 0);

    }

    private void setUpBackToMainActivityButton() {
        Button BackToMainBtn = (Button) findViewById(R.id.back_to_main_activity);
        BackToMainBtn.setText("Name = " + name + ", Age = " + age);
        BackToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // static method as the call will be made from a class level
    // also returns an Intent
    // context is passed in from the calling Activity
    public static Intent makeIntent(Context context, String personName, int age) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_NAME, personName);
        intent.putExtra(EXTRA_AGE, age);
        return intent;
    }
}

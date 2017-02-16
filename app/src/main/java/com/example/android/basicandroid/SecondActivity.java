package com.example.android.basicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setUpBackToMainActivityButton();
    }

    private void setUpBackToMainActivityButton() {
        Button BackToMainBtn = (Button) findViewById(R.id.back_to_main_activity);
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
    public static Intent makeIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }
}

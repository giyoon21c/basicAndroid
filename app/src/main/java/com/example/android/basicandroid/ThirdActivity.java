package com.example.android.basicandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    public static final String DA_MESSAGE = "daMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setupEndActivityButton();
    }

    public void setupEndActivityButton() {
        Button backtoMainActivityBtn = (Button) findViewById(R.id.button3);
        backtoMainActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.editText3);
                String message = edit.getText().toString();

                // pass back intent to the mainActivity
                Intent intent = new Intent();
                intent.putExtra(DA_MESSAGE, message);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    // static method as the call will be made from a class level
    // also returns an Intent
    // context is passed in from the calling Activity
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ThirdActivity.class);
        return intent;
    }

    public static String getResultMessage(Intent intent) {
        return intent.getStringExtra(DA_MESSAGE);
    }

}

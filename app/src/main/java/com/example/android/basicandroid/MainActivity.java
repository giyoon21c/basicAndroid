package com.example.android.basicandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoInitialApp";

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wire up the button to do stuff
        // .. get the button using findViewBy w/ resource id
        Button btn = (Button) findViewById(R.id.btnDoMagic);
        btn.setOnClickListener(new View.OnClickListener() {
            // .. set what happends when the user clicks
            @Override
            public void onClick(View v) {
                Log.i(TAG, "This is a magic log msg!");
                Toast.makeText(MainActivity.this, "It's magic!", Toast.LENGTH_SHORT)
                        .show();
                TextView msg = (TextView) findViewById(R.id.textView);
                count++;

                // using strings.xml to define
                String message = getString(R.string.how_about_now);
                msg.setText(message + count);
            }
        });

        // setup a launch botton from scratch!
        setUpLaunchButton();
    }

    /**
     *  setting up setOnClickListener
     */
    private void setUpLaunchButton() {
        Button launchButton = (Button) findViewById(R.id.launch_button);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Launch!", Toast.LENGTH_SHORT)
                        .show();

                // note the MainActivity.this and SecondActivity.class syntax
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //startActivity(intent);

                // instead of above method, we can ask secondActivity to give us Intent that
                // we can use while giving the calling activity's context...
                // called "static factory method"
                Intent intent = SecondActivity.makeIntent(MainActivity.this, "Bob", 101);
                startActivity(intent);

            }
        });
    }
}

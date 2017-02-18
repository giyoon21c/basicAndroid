package com.example.android.basicandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoInitialApp";
    public static final int REQUEST_CODE_FOR_MESSAGE = 1004;

    private int count = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
                msg.setText(message + " count: " + count);
            }
        });

        // setup a launch botton from scratch!
        setUpLaunchButton();
        setupThirdButton();
        //createRadioButtons();
        //setupPrintSelectedButton();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * setting up setOnClickListener
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
                // Intent intent = SecondActivity.makeIntent(MainActivity.this, "Bob", 101);
                // startActivity(intent);

                // using class PetRock
                PetRock rocky = new PetRock("charlie", 56);
                Intent intent = SecondActivity.makeIntent(MainActivity.this, rocky);
                startActivity(intent);
            }
        });
    }

    /**
     * setting up third button and onClickListener
     */
    private void setupThirdButton() {
        Button launch2Button = (Button) findViewById(R.id.button2);
        launch2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ThirdActivity.makeIntent(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_FOR_MESSAGE);
            }
        });
    }

    // need to override result coming back from ThirdActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_MESSAGE:
                if (resultCode == Activity.RESULT_OK) {
                    // get message and do something with it
                    //String message = data.getStringExtra("daMessage");
                    String message = ThirdActivity.getResultMessage(data);
                    Log.i("MyApp", "Result message is: " + message);
                } else {
                    Log.i("MyApp", "Activity cancelled!");
                }
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

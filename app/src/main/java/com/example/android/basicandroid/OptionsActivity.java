package com.example.android.basicandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioButtons();
        setupPrintSelectedButton();
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, OptionsActivity.class);
        return intent;
    }

    private void createRadioButtons() {
        // find the radio group, then add buttons to it
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_install_size);

        int[] numPanels = getResources().getIntArray(R.array.num_solar_panels);

        // create buttons
        for (int i = 0; i < numPanels.length; i++) {
            final int numPanel = numPanels[i];
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.solar_panels, numPanel));

            // TODO: add onclick call backs
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "You clicked " + numPanel, Toast.LENGTH_SHORT)
                            .show();
                }
            });
            // TODO: add to radio group
            group.addView(button);
        }
    }

    private void setupPrintSelectedButton() {
        Button btn = (Button) findViewById(R.id.find_selected);
        final int[] numPanels = getResources().getIntArray(R.array.num_solar_panels);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the button group and checkedRadioButtonId
                RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_install_size);
                int idOfSelected = group.getCheckedRadioButtonId();

                if (idOfSelected != -1) {
                    RadioButton radioButton = (RadioButton) findViewById(idOfSelected);
                    String message = radioButton.getText().toString();
                    Toast.makeText(getApplicationContext(), "Selected button's text is: " +
                            message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No button selected!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}

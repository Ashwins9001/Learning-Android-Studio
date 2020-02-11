package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

//require adapter to assign ArrayAdapter to spinner, adapters used to connect data
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();

        //ref extra and activity it is sent from
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview = findViewById(R.id.order_textview);
        textview.setText(message);

        Spinner spinner = findViewById(R.id.label_spinner);
        //onItemSelectedListener interface uses callback onItemSelected() and onNothingSelected()
        //methods to do something after selection
        //implement interface below, then define its methods at bottom
        if(spinner != null)
        {
            spinner.setOnItemSelectedListener(this);
        }

        //create ArrayAdapter, returns view for each obj in collection
        //Creates view by calling Object#toString() and placing result in TextView
        //define context, data array, and layout type to create views
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        //specify layout when choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner, so AdapterView passed during callback methods
        if(spinner != null)
        {
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        //confirm radio button is checked/clicked, cast view to RadioButton
        boolean checked = ((RadioButton) view).isChecked();
        //check which one was clicked by referencing view id
        switch(view.getId()) {
            case R.id.sameday:
                if(checked)
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if(checked)
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if(checked)
                    displayToast(getString(R.string.pickup));
                break;
            default:
                break;
        }

    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    //<?> wildcard used in generics to rep any data type can be implemented in AdapterView
    //retrieve ith item from array, display it as toast
    //works alongside ArrayAdapter
    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    // Interface callback for when no spinner item is selected.
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing.
    }
}

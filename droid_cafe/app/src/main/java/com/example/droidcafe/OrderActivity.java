package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        //ref extra and activity it is sent from
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview = findViewById(R.id.order_textview);
        textview.setText(message);
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
}

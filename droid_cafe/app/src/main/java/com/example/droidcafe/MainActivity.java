package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String mOrderMessage;
    public static final String EXTRA_MESSAGE = "com.example.droidcafe.extra.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //explicit intent, specifically state OrderActivity will fulfill it
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                //provide key-val for OrderActivity to decode in onCreate()
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void displayToast(String message)
    {
        //Context provides access to application specific resources on class, extension of Activity
        //Pass application context when unassociated with an activity/not implementing lib
        //Done to prevent memory leaks (ref to activity prevents garb collection upon destrucction)
        //Applies to newnly created obj, or to access comp implicitly to check ehat's happening
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //create click handlers to do something when onClick() gets called
    //Must take View (UI elem ref) param, be public, output void
    public void showDonutOrder(View view)
    {
        mOrderMessage = getString(R.string.donut_order_message);
        //extracted via string res
        displayToast(mOrderMessage);
    }
    public void showIceCreamOrder(View view)
    {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        //extracted via string res
        displayToast(mOrderMessage);
    }
    public void showFroyoOrder(View view)
    {
        mOrderMessage = getString(R.string.froyo_order_message);
        //extracted via string res
        displayToast(mOrderMessage);
    }
}


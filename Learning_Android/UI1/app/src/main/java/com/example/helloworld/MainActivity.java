package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    //create textview to update count and display
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call id of view, ensure it is a textview
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        //create a toast, short warning, give it an associated activity (this for main)
        //activity extends context, providing connection to resources, database
        //using activity context to attach toast lifecycle to main
        //identify string resource from XML via r.string, then ref one to use (toast)
        Toast t1 = Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT);
        t1.show();
    }

    //runs on button click, incrementing it each time
    public void showCount(View view) {
        mCount++;
        //if textview exists, convert integer mCount to a string and display it
        if(mShowCount!=null)
            mShowCount.setText(Integer.toString(mCount));
    }
}

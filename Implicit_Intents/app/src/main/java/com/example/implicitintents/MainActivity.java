package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //hold EditText obj for URI
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);

    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        //ACTION_VIEW views given data, in this case webpage, diff from before
        //Where current context and specific component required
        //Implicit intent fires off apps that COULD run activity, let user choose
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        //Return activity component to handle event, multiple activities can be chosen from baesd
        //On priority
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle intent");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        //Added geo search query tag will automatically open maps if installed
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle intent");
        }
    }
}

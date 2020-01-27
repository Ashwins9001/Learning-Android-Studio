package com.example.twoactivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.twoactivies.extra.REPLY";
    private EditText mReply;
    private static String LOG_TAG = SecondActivity.class.getSimpleName();

    //pass intent back to main with data being URI for specific action or
    //sending through intent extras if more info that's not URI required
    //intent extras are key-value pairs stored in Bundles (similar to hashtable)
    //insert them into Bundle when sending, then decode on receive

    //for things such as rotation: onPause->onStop->onDestroy->onStart->onResume
    //essentially destroy and remake activity
    //must track state of activity else user data lost
    //Activity state stored in bundle obj called instance state
    //system passes default information to bundle obj when activity stopped, then bundle obj
    //passed to a new activity instance to restore details, giving appearance of unchanged data
    //use onSaveStateInstance() to keep data, called b/w onPause, onStop
    //once app restarted instance state gets lost, to store user data b/w sessions, use database

    //SecondActivity layout and state generated from intent, even if recreated intent still there
    //thus data (extras) and config unchanged
    //EditText elements also maintain information when changed
    //TextView elements must be configured
    @Override
    public void onStart(){ //activity visible to user
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
    //on startup: onCreate ->onStart->onResume
    @Override
    public void onPause(){ //activity being left, pause animations/music playback, etc.
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }
    @Override
    public void onResume(){ //allows new activity to foreground to resume operations
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }
    @Override
    public void onStop(){ //activity no longer visible to user (either due to new one or before termination)
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //receive intent that activated SecondActivity
        mReply = findViewById(R.id.editText_second);
        Intent intent = getIntent(); //only one activity ref by intent, else input URI to specify
        //putExtra string into Bundle, therefore getStringExtra references key EXTRA_MESSAGE, decodes it for value
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //missing from strings.xml as it is not defined before
        TextView textview = findViewById(R.id.text_message);
        textview.setText(message); //find text view obj, set its text to message received via extras from main activity intent
    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        //if intent successful, print it out
        setResult(RESULT_OK, replyIntent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish(); //finish calls onDestroy to terminate current activity, return to parent
    }
}

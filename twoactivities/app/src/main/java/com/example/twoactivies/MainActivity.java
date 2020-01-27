package com.example.twoactivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    //identify
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivies.extra.MESSAGE";
    private EditText mMessageEditText;
    //different EditText input type configures various keyboards/acceptable characters
    //only single copy of log_tag created and shared amongst class instances
    //MainActivity.class returns Class object, getSimpleName() method for class name
    private static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //R.id is identifier for obj
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EditText object refers elem
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
        Log.d(LOG_TAG, "------");
        Log.d(LOG_TAG, "onCreate");
    }
    //for all lifecycle methods, are required to override parent class and call its instance via super
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

    //define onClick event via XML for button
    //pass in View which is base class for all widgets (buttons, textfields, etc.)
    //views are rectangular areas responsible for drawing and event handling
    public void launchSecondActivity(View view) {
        //on button click, this method gets called
        //logs debug message, LOG_TAG string used to define source of it
        Log.d(LOG_TAG, "button clicked");
        //AndroidManifest.xml used to define intent hierarchies, filters (to select an activity if intent ref many),
        //app comp for activities, receivers, service, providers
        //intent changes activity
        Intent intent = new Intent(this, SecondActivity.class); //pass current activity instace and second activity ref
        String message = mMessageEditText.getText().toString(); //receive text from obj and convert to str
        //store into bundle via putExtra, first elem key, second value
        intent.putExtra(EXTRA_MESSAGE, message);
        //when user completes subsequent activity (SecondActivity finishes), request code gets returned (TEXT_REQUEST)
        //result code too (RESULT_OK or RESULT_CANCELLED) and an intent that carries result data
        //Three parameters are an input for next system call: onActivityResult()
        startActivityForResult(intent, TEXT_REQUEST); //intent allows user to switch to other activity
        //define second activity as child under first via XML in androidManifest, so arrow to return back automatically appears
    }

    //define onActivityResult with override to show received data from SecondActivity
    //startActivityForResult->Second Activity->finish()->First Activity->onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //when subclass is created, parent class implicitly created as well
        //super refers immediate parent's class obj

        //call recursively, referencing parent activity and passing in its system parameters
        //get intent extra from its intent-data
        //requestCode is used to do various actions on activity callback
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST)
        {
            if(resultCode == RESULT_OK) //confirm that second activity finished and terminated
            {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}

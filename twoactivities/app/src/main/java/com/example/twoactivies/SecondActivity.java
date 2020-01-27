package com.example.twoactivies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.twoactivies.extra.REPLY";
    private EditText mReply;
    //pass intent back to main with data being URI for specific action or
    //sending through intent extras if more info that's not URI required
    //intent extras are key-value pairs stored in Bundles (similar to hashtable)
    //insert them into Bundle when sending, then decode on receive
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
        finish(); //finish calls onDestroy to terminate current activity, return to parent
    }
}
